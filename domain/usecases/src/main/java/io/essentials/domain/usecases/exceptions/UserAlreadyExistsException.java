package io.essentials.domain.usecases.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String email) {
        super(email);
    }
}
