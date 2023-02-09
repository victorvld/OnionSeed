package io.essentials.usecases.login.interactor;

import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.presenter.OutputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.usecases.login.response.LoginResponse;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.usecases.login.request.LoginRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.essentials.domain.usecases.context.Context.passwordEncoder;
import static io.essentials.domain.usecases.context.Context.repository;
import static io.essentials.domain.usecases.context.Context.sessionIdGenerator;

public class LoginInteractor implements InputBoundary {
    private final OutputBoundary presenter;

    public LoginInteractor(OutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public Response execute(Request request) {
        var loginRequest = (LoginRequest) request;
        var username = loginRequest.username();
        var password = loginRequest.password();
        Map<String, String> result = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (repository.isUserRegistered(username)) {
            if (repository.checkPassword(username, passwordEncoder.encode(password))) {
                if (repository.findSessionTokenByUsername(username).isPresent()) {
                    // TODO: 2/8/23 Check also if the token has expired or not.
                    errors.put("userAlreadyLogged", "User is already logged");
                } else {
                    var sessionToken = sessionIdGenerator.generate();
                    var expiresAt = LocalDateTime.now().plus(Duration.ofMinutes(30L)).format(DateTimeFormatter.ISO_DATE);
                    var userSession = repository.createUserSession(username, sessionToken, expiresAt);
                    result.put("sessionToken", userSession.sessionToken());
                    result.put("sessionTokenExpiresAt", userSession.expiresAt());
                }
            } else {
                errors.put("incorrectPasswordOrNonExistingAccount", "Your password is incorrect or this account doesn’t exist.");
            }
        } else {
            errors.put("incorrectPasswordOrNonExistingAccount", "Your password is incorrect or this account doesn’t exist.");
        }
        return presenter.present(new LoginResponse(result, errors));
    }
}
