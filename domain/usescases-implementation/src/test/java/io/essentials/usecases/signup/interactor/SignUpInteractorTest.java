package io.essentials.usecases.signup.interactor;

import io.essentials.config.AppConfig;
import io.essentials.usecases.signup.exceptions.UserAlreadyExistsException;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.domain.usecases.responder.SignUpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignUpInteractorTest {

    private SignUpInteractor interactor;

    @BeforeEach
    void setUp() {
        AppConfig.setupUserContext();
        interactor = new SignUpInteractor();
    }

    @Test
    void createNewUser() {
        var email = "u@e.io";
        var password = "123";
        var name = "name";
        var surname = "surname";
        var form = new SignUpForm(email, password, name, surname);

        SignUpResponse response = (SignUpResponse) interactor.execute(form);

        Assertions.assertAll(
                "heading",
                () -> assertEquals(response.user().getFirstName(), name),
                () -> assertEquals(response.user().getLastName(), surname),
                () -> assertEquals(response.user().getEmail(), email)
        );
    }

    @Test
    void createExistingUser() {
        var email = "u@e.io";
        var password = "123";
        var name = "name";
        var surname = "surname";
        var form = new SignUpForm(email, password, name, surname);

        interactor.execute(form);

        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> interactor.execute(form));

        assertEquals(email, exception.getMessage());
    }

}
