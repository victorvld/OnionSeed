package io.victorvld.adapter.controller;

import io.victorvld.adapter.model.LoginForm;
import io.victorvld.domain.usecases.interactor.InputBoundary;
import io.victorvld.domain.usecases.requester.Request;
import io.victorvld.domain.usecases.responder.Response;
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
        Request r = new LoginForm(username, password);

        controller.handle(r);

        assertTrue(loginInteractorSpy.wasExecuteMethodCalled());
        assertEquals(loginInteractorSpy.getRequest().username(), username);
        assertEquals(loginInteractorSpy.getRequest().password(), password);
    }

}

class InputBoundarySpy implements InputBoundary {

    private boolean executeMethodWasCalled;
    private LoginForm request;

    @Override
    public Response execute(Request request) {
        this.executeMethodWasCalled = true;
        this.request = (LoginForm) request;
        return null;
    }

    public boolean wasExecuteMethodCalled() {

        return this.executeMethodWasCalled;
    }

    public LoginForm getRequest() {
        return request;
    }
}