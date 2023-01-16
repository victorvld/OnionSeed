package io.essentials.adapter.validator;

import io.essentials.adapter.validator.exceptions.UserValidationException;
import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleUserValidatorTest {

    private Validator<User> validator;

    @BeforeEach
    void setUp() {
        validator = new SimpleUserValidator();
    }

    private static Stream<Arguments> provideUsersForValidate() {
        return Stream.of(
                Arguments.of(null, "User should not be null"),
                Arguments.of(User.builder().email("email").build(), "None of the User fields should be null"),
                Arguments.of(User.builder().firstName("").lastName("lastname").email("email").build(), "First name should not be blank"),
                Arguments.of(User.builder().firstName("firstname").lastName("lastname").email("").build(), "Email should not be blank"),
                Arguments.of(User.builder().firstName("firstname").lastName("").email("email").build(), "Last name should not be blank")
        );
    }

    @ParameterizedTest
    @MethodSource("provideUsersForValidate")
    void userValidationExceptionsTest(User u, String message) {
        Exception exception = assertThrows(UserValidationException.class,
                () -> validator.validate(u));

        assertEquals(message, exception.getMessage());
    }
}