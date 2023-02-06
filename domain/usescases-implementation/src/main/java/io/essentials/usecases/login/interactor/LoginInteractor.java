package io.essentials.usecases.login.interactor;

import io.essentials.domain.usecases.context.Context;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.usecases.login.request.LoginRequest;
import io.essentials.usecases.login.response.LoginResponse;

public class LoginInteractor implements InputBoundary {
    @Override
    public Response execute(Request request) {
        Response response = new LoginResponse(false, null);
        var loginRequest = (LoginRequest) request;
        var username = loginRequest.username();
        var password = loginRequest.password();
        if (Context.repository.findByEmail(username).isPresent()) {
            if (Context.repository.checkPassword(username, password)) {
                // Correct Password
                if (Context.repository.findSessionTokenByEmail(username).isPresent()) {
                    // Failed login attempt, reason: User is already logged.
                } else {
                    //  Login successfully
                    var sessionToken = Context.sessionIdGenerator.generate();
                    var userSession = Context.repository.createUserSession(username, sessionToken);
                    response = new LoginResponse(true, userSession.sessionToken());
                }
            } else {
                // Failed login attempt, reason: Incorrect Password.
            }
        } else {
            // Failed login attempt, reason: Unknown User.
        }
        return response;
    }
}
