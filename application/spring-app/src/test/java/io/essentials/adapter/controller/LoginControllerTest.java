package io.essentials.adapter.controller;

import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.usecases.login.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * These tests are made to check whether the interactor is called
 * by the login controller properly.
 * This means that the execute method is called with the correct parameters.
 */
class LoginControllerTest {

    private InputBoundarySpy loginInteractorSpy;

    @BeforeEach
    void init() {
        loginInteractorSpy = new InputBoundarySpy();
    }

    @Test
    @DisplayName("login")
    void loginTest() {
        var controller = new LoginController(loginInteractorSpy);

        String username = "my user";
        String password = "my password";
        Request r = new LoginRequest(username, password);

        controller.handle(r);

        assertTrue(loginInteractorSpy.wasExecuteMethodCalled());
        assertEquals(loginInteractorSpy.getRequest().username(), username);
        assertEquals(loginInteractorSpy.getRequest().password(), password);
    }

}

class InputBoundarySpy implements InputBoundary {

    private boolean executeMethodWasCalled;
    private LoginRequest request;

    @Override
    public Response execute(Request request) {
        this.executeMethodWasCalled = true;
        this.request = (LoginRequest) request;
        return null;
    }

    public boolean wasExecuteMethodCalled() {

        return this.executeMethodWasCalled;
    }

    public LoginRequest getRequest() {
        return request;
    }
}
