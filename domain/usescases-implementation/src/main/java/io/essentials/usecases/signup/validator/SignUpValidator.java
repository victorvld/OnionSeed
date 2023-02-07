package io.essentials.usecases.signup.validator;

import io.essentials.domain.usecases.requester.Request;
import io.essentials.usecases.signup.exceptions.UserValidationException;
import io.essentials.domain.usecases.requester.SignUpForm;

public class SignUpValidator {
    public static void validate(Request request) {
        var form = (SignUpForm) request;
        if (form == null) throw new UserValidationException("SignUp form should not be null");
        if (form.email() == null || form.firstName() == null || form.lastName() == null) {
            throw new UserValidationException("None of the SignUp form fields should be null");
        } else {
            if (form.email().isBlank()) throw new UserValidationException("Email should not be blank");
            if (form.firstName().isBlank()) throw new UserValidationException("First name should not be blank");
            if (form.lastName().isBlank()) throw new UserValidationException("Last name should not be blank");
        }
    }
}
