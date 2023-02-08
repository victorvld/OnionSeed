package io.essentials.adapter.controller;


import io.essentials.adapter.controller.doubles.InputBoundarySpy;
import io.essentials.domain.usecases.requester.Request;

import io.essentials.usecases.login.request.LoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * These tests are made to check whether the interactor is called
 * by the {@link LoginController} properly.
 * This means that the {@link LoginController#handle(Request)} method is called with the correct parameters.
 */
class LoginControllerTest {

    private InputBoundarySpy inputBoundarySpy;

    @BeforeEach
    void init() {
        inputBoundarySpy = new InputBoundarySpy();
    }

    @Test
    @DisplayName("Login Controller handles request properly")
    void whenLoginControllerHandlesLoginRequestThenInputBoundaryIsCalledWithRightArguments() {
        var controller = new LoginController(inputBoundarySpy);
        var username = "my user";
        var password = "my password";
        var r = new LoginRequest(username, password);

        controller.handle(r);

        var request = (LoginRequest) inputBoundarySpy.getRequest();
        assertAll(
                () -> assertTrue(inputBoundarySpy.wasExecuteMethodCalled()),
                () -> Assertions.assertEquals(request.username(), username),
                () -> Assertions.assertEquals(request.password(), password)
        );
    }

}
