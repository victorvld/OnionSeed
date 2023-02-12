package io.essentials.usecases.signup.interactor;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.context.Context;
import io.essentials.usecases.signup.exceptions.UserAlreadyExistsException;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.domain.usecases.responder.SignUpResponse;
import io.essentials.usecases.signup.validator.SignUpValidator;

public final class SignUpInteractor implements InputBoundary {

    @Override
    public Response execute(Request request) {
        var form = (SignUpForm) request;
        var email = form.email();
        // The result should be included in the response
        SignUpValidator.validate(form);
        // This should be included in the response
        if (Context.repository.findByUsername(email).isPresent()) {
            throw new UserAlreadyExistsException(email);
        }
        var userToSave = User.builder()
                .id(Context.idGenerator.generate())
                .email(email)
                .password(Context.passwordEncoder.encode(form.password()))
                .lastName(form.lastName())
                .firstName(form.firstName())
                .build();

        // TODO: 2/7/23 The response must be design within the response model.
        return new SignUpResponse(Context.repository.create(userToSave));
    }
}

