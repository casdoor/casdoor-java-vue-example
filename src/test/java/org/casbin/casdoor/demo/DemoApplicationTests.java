package org.casbin.casdoor.demo;

import org.casbin.casdoor.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void accountNeedsASignedInUser() throws Exception {
        mvc.perform(get("/api/get-account"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value("error"));
    }

    @Test
    void accountIsTheUserOfTheSessionUntilSigningOut() throws Exception {
        User user = new User();
        user.owner = "casbin";
        user.name = "alice";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("casdoorUser", user);

        mvc.perform(get("/api/get-account").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.data.name").value("alice"));

        mvc.perform(post("/api/signout").session(session)).andExpect(status().isOk());
        mvc.perform(get("/api/get-account")).andExpect(status().isUnauthorized());
    }

    @Test
    void onlyTheFrontendMayCallTheApis() throws Exception {
        mvc.perform(options("/api/get-account")
                        .header(HttpHeaders.ORIGIN, "http://localhost:8080")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080"))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"));
        mvc.perform(options("/api/get-account")
                        .header(HttpHeaders.ORIGIN, "https://evil.example.com")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andExpect(status().isForbidden());
    }
}
