package io.essentials.adapter.controller;

import io.essentials.adapter.controller.doubles.InputBoundarySpy;
import io.essentials.domain.usecases.requester.LoginRequest;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.requester.SignUpForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * These tests are made to check whether the interactor is called
 * by the {@link SignUpController} properly.
 * This means that the {@link SignUpController#handle(Request)} method is called with the correct parameters.
 */
public class SignUpControllerTest {

    private InputBoundarySpy inputBoundarySpy;

    @BeforeEach
    void init() {
        inputBoundarySpy = new InputBoundarySpy();
    }

    @Test
    @DisplayName("Sign Up Controller handles request properly")
    void whenSignUpControllerHandlesSignUpFormThenInputBoundaryIsCalledWithRightArguments() {
        var controller = new SignUpController(inputBoundarySpy);

        String username = "my user";
        String password = "my password";
        String lastName = "my last name";
        String firstName = "my name";
        Request r = new SignUpForm(username, password, firstName, lastName);

        controller.handle(r);

        var request = (SignUpForm) inputBoundarySpy.getRequest();
        assertAll(
                () -> assertTrue(inputBoundarySpy.wasExecuteMethodCalled()),
                () -> Assertions.assertEquals(request.email(), username),
                () -> Assertions.assertEquals(request.password(), password),
                () -> Assertions.assertEquals(request.firstName(), firstName),
                () -> Assertions.assertEquals(request.lastName(), lastName)
        );
    }

}
