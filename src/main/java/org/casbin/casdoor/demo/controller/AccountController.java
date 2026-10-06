package org.casbin.casdoor.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.exception.AuthException;
import org.casbin.casdoor.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private static final String USER = "casdoorUser";
    private static final String ACCESS_TOKEN = "casdoorAccessToken";

    private final AuthService authService;

    public AccountController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Exchanges the code from Casdoor for an access token, verifies the token and keeps the user in the session.
     * The frontend checks the state before calling this.
     */
    @PostMapping("/signin")
    public ResponseEntity<Response> signin(@RequestParam String code, @RequestParam String state,
                                           HttpServletRequest request) {
        String token;
        User user;
        try {
            token = authService.getOAuthToken(code, state);
            user = authService.parseJwtToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.error(e.getMessage()));
        }

        // a new session ID for the signed-in user, against session fixation
        request.getSession();
        request.changeSessionId();
        request.getSession().setAttribute(USER, user);
        request.getSession().setAttribute(ACCESS_TOKEN, token);
        return ResponseEntity.ok(Response.ok(null));
    }

    @GetMapping("/get-account")
    public ResponseEntity<Response> getAccount(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.error("Not signed in"));
        }
        return ResponseEntity.ok(Response.ok(user));
    }

    @PostMapping("/signout")
    public Response signout(HttpSession session) {
        Object token = session.getAttribute(ACCESS_TOKEN);
        if (token != null) {
            try {
                // also end the user's session in Casdoor, so that signing in again asks for the password
                authService.logoutCurrentSession(token.toString());
            } catch (RuntimeException e) {
                // the Casdoor session may already have ended
            }
        }
        session.invalidate();
        return Response.ok(null);
    }
}
