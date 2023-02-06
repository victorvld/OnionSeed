package io.essentials.domain.usecases.interactor;

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
}
