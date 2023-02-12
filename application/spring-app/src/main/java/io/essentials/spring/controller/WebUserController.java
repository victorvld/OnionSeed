package io.essentials.spring.controller;

import io.essentials.adapter.controller.login.LoginController;
import io.essentials.adapter.controller.signup.SignUpController;
import io.essentials.adapter.model.WebUser;
import io.essentials.adapter.presenter.response.ViewResponse;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.domain.usecases.responder.SignUpResponse;
import io.essentials.usecases.login.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public String home() {
        return "login_page.html";
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    WebUser signUp(SignUpForm form) {
        SignUpResponse response = (SignUpResponse) signUpController.handle(form);
        return WebUser.toUserWeb(response.user());
    }

    @PostMapping(value = "/home", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> login(LoginRequest form) {
        var r = (ViewResponse) loginController.handle(form);
        return ResponseEntity.status(r.statusCode())
                // TODO: 2/10/23 translate this header to HTTP header.
                //.header(r.headers())
                .body(r.content());
    }
}
