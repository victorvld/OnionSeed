package io.essentials.adapter.controller;

import io.essentials.adapter.model.WebUser;
import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.UserInteractor;

public class UserController {

    private final UserInteractor userInteractor;

    public UserController(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    public WebUser createUser(WebUser wUser) {
        User user = userInteractor.create(wUser.toUser());
        return WebUser.toUserWeb(user);
    }
}
