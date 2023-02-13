package io.essentials.usecases.login.interactor;

import io.essentials.domain.usecases.context.Context;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.domain.usecases.requester.LoginRequest;
import io.essentials.domain.usecases.responder.LoginResponse;

import java.util.Optional;

public class LoginInteractor implements InputBoundary {
    @Override
    public Response execute(Request request) {
        var loginRequest = (LoginRequest) request;
        var username = loginRequest.username();
        var password = loginRequest.password();
        if (Context.repository.findByEmail(username).isPresent()) {
            if (Context.repository.checkPassword(username, Context.passwordEncoder.encode(password))) {
                // Correct Password
                if (Context.repository.findSessionTokenByEmail(username).isPresent()) {
                    // Failed login attempt, reason: User is already logged.
                    return new LoginResponse(false, Optional.empty(), Optional.of("User is already logged"));
                } else {
                    //  Login successfully
                    var sessionToken = Context.sessionIdGenerator.generate();
                    var userSession = Context.repository.createUserSession(username, sessionToken);
                    return new LoginResponse(true, Optional.of(userSession.sessionToken()), Optional.empty());
                }
            } else {
                // Failed login attempt, reason: Incorrect Password.
                return new LoginResponse(false, Optional.empty(), Optional.of("Incorrect Password"));
            }
        } else {
            // Failed login attempt, reason: Unknown User.
            return new LoginResponse(false, Optional.empty(), Optional.of("Unknown User"));
        }
    }
}
