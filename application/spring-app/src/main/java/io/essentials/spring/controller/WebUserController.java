package io.essentials.spring.controller;

import io.essentials.adapter.controller.UserController;
import io.essentials.adapter.model.LoginForm;
import io.essentials.adapter.model.WebUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebUserController {
    @Autowired
    private UserController controller;

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

    @PostMapping(value = "/home", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String login(LoginForm form, HttpServletResponse response) {
        // @RequestParam Map<String, String> map
        //String username = map.get("email");
        //String password = map.get("password");
        //Todo : create an interface for the responses instead of using a string.
        String result = controller.login(form.username(), form.password());
        if (result != null) {
            response.addCookie(new Cookie(form.username(), result));
            return "home.html";
        } else {
            return "index.html";
        }
    }

}


