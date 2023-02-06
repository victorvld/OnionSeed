package io.essentials.adapter.controller;

import io.essentials.adapter.model.WebUser;
import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.interactor.UserInteractor;

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
