package io.essentials.spring.controller;

import io.essentials.adapter.controller.LoginController;
import io.essentials.adapter.controller.UserController;
import io.essentials.adapter.model.WebUser;
import io.essentials.spring.utils.CookiesUtils;
import io.essentials.usecases.login.request.LoginRequest;
import io.essentials.usecases.login.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private LoginController loginController;

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
    String login(LoginRequest form, HttpServletResponse response, HttpServletRequest request) {
        //Todo : create an interface for the responses instead of using a string.
        LoginResponse lr = (LoginResponse) loginController.handle(form);
        // TODO: 2/6/23 All the logic here will have to be moved to the presenter
        //  as soon as the response model is created.
        if (lr.success()) {
            var domain = String.format("%s", request.getServerName());
            CookiesUtils.createCookie("sessionToken", lr.sessionToken(), domain).ifPresent(response::addCookie);
            return "home.html";
        } else {
            return "index.html";
        }
    }

}


