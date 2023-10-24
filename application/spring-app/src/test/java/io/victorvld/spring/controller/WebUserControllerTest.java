package io.victorvld.spring.controller;

import io.victorvld.adapter.controller.UserController;
import io.victorvld.adapter.model.WebUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private UserController userController;

    @Test
    public void whenEmptyPathIsRequestThenHomePageIsReturn() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void whenUsersPathIsRequestWithWebUserThenUserControllerIsCalledAndStatusIs2XX() throws Exception {
        var webUser = new WebUser("u@email.com", "password", "victor", "arranz");
        var requestBody = """
                {
                    "firstName": "victor",
                    "lastName": "arranz",
                    "email": "u@email.com",
                    "password" : "password"
                }""";
        var responseBody = "{\"email\":\"u@email.com\",\"lastName\":\"arranz\",\"firstName\":\"victor\"}";
        Mockito.when(userController.createUser(webUser)).thenReturn(new WebUser("u@email.com", null, "victor", "arranz"));

        this.mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andExpect(content().string(responseBody));
    }

    @Test
    void whenUsersPathIsRequestWithRequestBodyMissingThenResponseStatusIs4XX() throws Exception {
        this.mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void whenUnknownPathIsRequestThenResponseStatusIs4XX() throws Exception {
        this.mvc.perform(post("/unknown"))
                .andExpect(status().is4xxClientError());

    }
}