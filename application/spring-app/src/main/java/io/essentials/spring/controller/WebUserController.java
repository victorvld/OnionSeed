package io.essentials.spring.controller;

import io.essentials.adapter.controller.UserController;
import io.essentials.adapter.model.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WebUserController {

    private final UserController controller;

    @Autowired
    public WebUserController(final UserController controller) {
        this.controller = controller;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    // todo: 1/18/23 This should return an html since we are building a web application and not a rest api.
    @PostMapping("/users")
    @ResponseBody
    WebUser createNewAccount(@RequestBody WebUser newUser) {
        return controller.createUser(newUser);

    }

    @PostMapping("/home")
    String login(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        //create an interface for the responses instead of using a string.
        String response = controller.login(username, password);
        // TODO: 1/26/23 Implement login response ES-1
        // Login successful will retrieve a response that contains a session token + user home page.
        // Failed login attempt response will retrieve a response with the cause and will not redirect the page.
        return "home.html";
    }

}


