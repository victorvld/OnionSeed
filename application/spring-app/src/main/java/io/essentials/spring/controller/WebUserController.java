package io.essentials.spring.controller;

import io.essentials.adapter.controller.login.LoginController;
import io.essentials.adapter.controller.signup.SignUpController;
import io.essentials.adapter.model.WebUser;
import io.essentials.spring.utils.CookiesUtils;
import io.essentials.usecases.login.request.LoginRequest;
import io.essentials.usecases.login.response.LoginResponse;
import io.essentials.domain.usecases.requester.SignUpForm;

import io.essentials.domain.usecases.responder.SignUpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebUserController {
    @Autowired
    private SignUpController signUpController;

    @Autowired
    private LoginController loginController;

    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    // TODO: 2/7/23 Find how to send out the proper response.
    //  This includes error handling like wrong password, etc
    //  or successful response.
    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    WebUser signUp(SignUpForm form) {
        SignUpResponse response = (SignUpResponse) signUpController.handle(form);
        return WebUser.toUserWeb(response.user());
    }

    @PostMapping(value = "/home", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String login(LoginRequest form, HttpServletResponse response, HttpServletRequest request) {
        //Todo : create an interface for the responses instead of using a string.
        LoginResponse lr = (LoginResponse) loginController.handle(form);
        // TODO: 2/6/23 All the logic here will have to be moved to the presenter
        //  as soon as the response model is created.
        if (lr.errors().isEmpty()) {
            var domain = String.format("%s", request.getServerName());
            // TODO: 2/8/23 All this logic will be move to the LoginViewImplementation
            CookiesUtils.createCookie("sessionToken", lr.result().get("sessionToken"), domain).ifPresent(response::addCookie);
            return "home.html";
        } else {
            return "index.html";
        }
    }
}
