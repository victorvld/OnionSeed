package io.essentials.usecases.login.interactor;

import io.essentials.config.AppConfig;
import io.essentials.domain.usecases.requester.LoginRequest;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.domain.usecases.responder.LoginResponse;
import io.essentials.usecases.signup.interactor.SignUpInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;

    @BeforeEach
    void setUp() {
        loginInteractor = new LoginInteractor();
        AppConfig.setupUserContext();
        var SignUpForm1 = new SignUpForm("u@e.io", "123", "name", "surname");
        var SignupForm2 = new SignUpForm("alreadyLogged@e.io", "1234", "name", "surname");
        var signUpInteractor = new SignUpInteractor();
        signUpInteractor.execute(SignUpForm1);
        signUpInteractor.execute(SignupForm2);
        loginInteractor.execute(new LoginRequest("alreadyLogged@e.io", "1234"));
    }

    private static Stream<Arguments> loginFormProvider() {
        return Stream.of(
                // Incorrect Password
                Arguments.of(new LoginRequest("u@e.io", "1234"), false, false, "Incorrect Password"),
                //Unknown User
                Arguments.of(new LoginRequest("u2@e.io", "1234"), false, false, "Unknown User"),
                // Already logged
                Arguments.of(new LoginRequest("alreadyLogged@e.io", "1234"), false, false, "User is already logged")
        );
    }

    @ParameterizedTest
    @MethodSource("loginFormProvider")
    void loginUser(LoginRequest form, boolean expectSuccessResponse, boolean sessionTokenIsPresent, String reason) {
        var response = (LoginResponse) loginInteractor.execute(form);

        Assertions.assertAll(
                "heading",
                () -> assertEquals(response.success(), expectSuccessResponse),
                () -> assertEquals(response.sessionToken().isPresent(), sessionTokenIsPresent),
                () -> assertEquals(response.reason().get(), reason)
        );
    }

    @Test
    @DisplayName("successfulLogin")
    void successfulLoginTest() {
        var loginForm = new LoginRequest("u@e.io", "123");

        var response = (LoginResponse) loginInteractor.execute(loginForm);

        Assertions.assertAll(
                "heading",
                () -> assertTrue(response.success()),
                () -> assertTrue(response.sessionToken().isPresent()),
                () -> assertFalse(response.reason().isPresent())
        );
    }

}
