package io.essentials.usecases.signup.validator;

import io.essentials.usecases.signup.exceptions.UserValidationException;
import io.essentials.domain.usecases.requester.SignUpForm;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignUpValidatorTest {

    private static Stream<Arguments> provideUsersForValidate() {
        return Stream.of(
                Arguments.of(null, "SignUp form should not be null"),
                Arguments.of(new SignUpForm(null, null, null, null), "None of the SignUp form fields should be null"),
                Arguments.of(new SignUpForm("email", "password", " ", "last"), "First name should not be blank"),
                Arguments.of(new SignUpForm(" ", "password", "first", "last"), "Email should not be blank"),
                Arguments.of(new SignUpForm("email", "password", "first", " "), "Last name should not be blank")
        );
    }

    @ParameterizedTest
    @MethodSource("provideUsersForValidate")
    void signUpValidationExceptionsTest(SignUpForm form, String message) {
        Exception exception = assertThrows(UserValidationException.class,
                () -> SignUpValidator.validate(form));

        assertEquals(message, exception.getMessage());
    }
}
