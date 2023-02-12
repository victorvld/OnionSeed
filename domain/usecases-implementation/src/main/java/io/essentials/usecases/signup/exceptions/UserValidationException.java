package io.essentials.usecases.signup.exceptions;

public class UserValidationException extends RuntimeException {
    public UserValidationException(final String message) {
        super(message);
    }
}
