package org.casbin.casdoor.demo.controller;

import org.casbin.casdoor.demo.util.Response;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.exception.CasdoorAuthException;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//@CrossOrigin(allowCredentials = "true")
@Controller
public class AccountController {
    @Resource
    private CasdoorAuthService casdoorAuthService;

//    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
//    public String login() {
//       // System.out.println(casdoorAuthService.getSigninUrl("http://localhost:8081/callback"));
//        return "redirect:" + casdoorAuthService.getSigninUrl("http://localhost:8081/callback");
//    }


    @ResponseBody
    @PostMapping( "/api/signin")
    public Response callback(String code, String state, HttpSession session) {
        String token = "";
        CasdoorUser user = null;
        try {
            token = casdoorAuthService.getOAuthToken(code, state);
            user = casdoorAuthService.parseJwtToken(token);
        } catch (CasdoorAuthException e) {
            e.printStackTrace();
            return new Response("fail",null);
        }
        session.setAttribute("casdoorUser", user);
        return new Response("ok", null);
    }

    @ResponseBody
    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public Response getUser(HttpSession session) throws Exception {
        CasdoorUser user = (CasdoorUser) session.getAttribute("casdoorUser");
        if(user==null){
            return new Response("fail",null);
        }
        return new Response("ok",user);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout(HttpSession session) throws Exception {
        session.setAttribute("casdoorUser", null);
        return new Response("ok",null);
    }


}
