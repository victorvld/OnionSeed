package io.victorvld.adapter.controller;

import io.victorvld.adapter.model.WebUser;
import io.victorvld.domain.entities.User;
import io.victorvld.domain.usecases.interactor.UserInteractor;

public class UserController {

    private final UserInteractor userInteractor;

    public UserController(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    public WebUser createUser(WebUser webUser) {
        User user = userInteractor.create(webUser.toUser());
        return WebUser.toUserWeb(user);
    }

    public String login(String username, String password) {
        return userInteractor.login(username, password);
    }
}
