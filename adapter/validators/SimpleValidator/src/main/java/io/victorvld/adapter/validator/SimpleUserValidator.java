package io.victorvld.adapter.validator;


import io.victorvld.adapter.validator.exceptions.UserValidationException;
import io.victorvld.domain.entities.User;
import io.victorvld.domain.usecases.validator.Validator;

public class SimpleUserValidator implements Validator<User> {

    @Override
    public void validate(User user) {
        if (user == null) throw new UserValidationException("User should not be null");
        if(user.getEmail() == null || user.getFirstName() == null || user.getLastName() == null) {
            throw new UserValidationException("None of the User fields should be null");
        } else {
            if (user.getEmail().isBlank()) throw new UserValidationException("Email should not be blank");
            if (user.getFirstName().isBlank()) throw new UserValidationException("First name should not be blank");
            if (user.getLastName().isBlank()) throw new UserValidationException("Last name should not be blank");
        }
    }
}
