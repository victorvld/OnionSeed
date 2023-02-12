package io.essentials.adapter.presenter.login;

import io.essentials.adapter.presenter.doubles.DummyInteractorResponse;
import io.essentials.adapter.presenter.doubles.ViewSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPresenterTest {

    ViewSpy viewSpy;

    LoginPresenter presenter;

    @BeforeEach
    void init() {
        viewSpy = new ViewSpy();
        presenter = new LoginPresenter(viewSpy);
    }

    @Test
    void loginPresenterAndViewAreBoundTest() {
        presenter.present(new DummyInteractorResponse());

        assertTrue(viewSpy.wasGenerateViewMethodCalled());
    }

}
