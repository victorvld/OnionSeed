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
    WebUser newEmployee(@RequestBody WebUser newUser) {
        return controller.createUser(newUser);

    }

    @PostMapping("/home")
    String logIn(@RequestParam Map<String, String> map) {
        return "home.html";
    }

}


