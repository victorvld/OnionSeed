package io.essentials.domain.usecases;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.context.Context;
import io.essentials.domain.usecases.exceptions.UserAlreadyExistsException;

public final class UserInteractor {

    public User create(final User user) {
        Context.userValidator.validate(user);
        if (Context.repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        var userToSave = User.builder()
                .id(Context.idGenerator.generate())
                .email(user.getEmail())
                .password(Context.passwordEncoder.encode(user.getPassword()))
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();

        return Context.repository.create(userToSave);
    }

    public String login(String email, String password) {
        String response = null;
        if (Context.repository.findByEmail(email).isPresent()) {
            if (Context.repository.checkPassword(email, password)) {
                // Correct Password
                if (Context.repository.findSessionTokenByEmail(email).isPresent()) {
                    // Failed login attempt, reason: User is already logged.
                } else {
                    //  Login successfully
                    var sessionToken = Context.sessionIdGenerator.generate();
                    var userSession = Context.repository.createUserSession(email, sessionToken);
                    response = userSession.sessionToken();
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
