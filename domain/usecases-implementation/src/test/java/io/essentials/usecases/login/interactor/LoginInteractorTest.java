package io.essentials.usecases.login.interactor;

import io.essentials.config.AppConfig;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.usecases.doubles.OutputBoundarySpy;
import io.essentials.usecases.login.request.LoginRequest;
import io.essentials.usecases.login.response.LoginResponse;
import io.essentials.usecases.signup.interactor.SignUpInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    private OutputBoundarySpy presenterSpy;
    private SignUpInteractor signUpInteractor;

    @BeforeEach
    void setUp() {
        AppConfig.setupUserContext();
        this.presenterSpy = new OutputBoundarySpy();
        this.loginInteractor = new LoginInteractor(presenterSpy);
        this.signUpInteractor = new SignUpInteractor();
        signUpInteractor.execute(new SignUpForm("registeredUser@e.io", "correctPassword", "firstName", "lastName"));
    }

    @Test
    @DisplayName("OutputBoundary Binding Test")
    void presenterIsProperlyBoundedTest() {
        loginInteractor.execute(new LoginRequest("u", "p"));

        Assertions.assertTrue(presenterSpy.wasPresentMethodCalled());
    }

    @Test
    @DisplayName("Successful Login Test")
    void successfulLoginTest() {
        var loginForm = new LoginRequest("registeredUser@e.io", "correctPassword");

        loginInteractor.execute(loginForm);

        var spyResponse = (LoginResponse) presenterSpy.getResponse();
        Assertions.assertAll(
                () -> assertFalse(spyResponse.result().isEmpty()),
                () -> assertTrue(spyResponse.errors().isEmpty())
        );
    }

    @Nested
    @DisplayName("Tests for the method A")
    class A {

        @BeforeEach
        void setUp() {
            var SignupForm2 = new SignUpForm("alreadyLogged@e.io", "correctPassword", "firstName", "lastName");
            signUpInteractor.execute(SignupForm2);
            loginInteractor.execute(new LoginRequest("alreadyLogged@e.io", "correctPassword"));
        }
        private static Stream<Arguments> failedLoginAttemptsProvider() {
            return Stream.of(
                    // Incorrect Password
                    Arguments.of(new LoginRequest("registeredUser@e.io", "wrongPassword"), "incorrectPasswordOrNonExistingAccount", "Your password is incorrect or this account doesn’t exist."),
                    // Unknown User
                    Arguments.of(new LoginRequest("nonExistingUser@e.io", "correctPassword"), "incorrectPasswordOrNonExistingAccount", "Your password is incorrect or this account doesn’t exist."),
                    // Already logged
                    Arguments.of(new LoginRequest("alreadyLogged@e.io", "correctPassword"), "userAlreadyLogged", "User is already logged")
            );
        }

        @ParameterizedTest
        @DisplayName("Failed Login attempts Tests")
        @MethodSource("failedLoginAttemptsProvider")
        void loginUser(LoginRequest form, String errorType, String reason) {
            loginInteractor.execute(form);

            var spyResponse = (LoginResponse) presenterSpy.getResponse();
            Assertions.assertAll(
                    () -> assertTrue(spyResponse.result().isEmpty()),
                    () -> assertEquals(spyResponse.errors().get(errorType), reason)
            );
        }
    }
}
