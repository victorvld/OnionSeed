package io.victorvld.spring.controller;

import io.victorvld.adapter.controller.UserController;
import io.victorvld.adapter.model.LoginForm;
import io.victorvld.adapter.model.WebUser;
import io.victorvld.spring.utils.CookiesUtils;
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
    String login(LoginForm form, HttpServletResponse response, HttpServletRequest request) {
        //Todo : create an interface for the responses instead of using a string.
        String result = controller.login(form.username(), form.password());
        if (result != null) {
            var domain = String.format("%s", request.getServerName());
            CookiesUtils.createCookie("sessionToken", result, domain).ifPresent(response::addCookie);
            return "home.html";
        } else {
            return "index.html";
        }
    }

}


