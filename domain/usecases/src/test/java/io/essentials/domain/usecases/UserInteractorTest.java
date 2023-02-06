package io.essentials.domain.usecases;

import io.essentials.domain.entities.User;
import io.essentials.config.AppConfig;
import io.essentials.domain.usecases.exceptions.UserAlreadyExistsException;
import io.essentials.domain.usecases.interactor.UserInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInteractorTest {

    @BeforeAll
    static void beforeAll() {
        AppConfig.setupUserContext();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void createNewUser() {
        UserInteractor interactor = new UserInteractor();

        var user = User.builder()
                .firstName("Name").lastName("Surname")
                .email("e@email.io")
                .password("53614698")
                .build();

        User uResult = interactor.create(user);

        Assertions.assertAll(
                "heading",
                () -> assertEquals(user.getFirstName(), uResult.getFirstName()),
                () -> assertEquals(user.getLastName(), uResult.getLastName()),
                () -> assertEquals(user.getEmail(), uResult.getEmail()),
                () -> assertNotNull(uResult.getId())
        );
    }

    @Test
    void createExistingUser() {
        UserInteractor interactor = new UserInteractor();

        var user = User.builder()
                .firstName("Name").lastName("Surname")
                .email("example@email.io")
                .password("password1")
                .build();

        var existingUser = User.builder()
                .firstName("Name2").lastName("Surname2")
                .email("example@email.io")
                .password("password2")
                .build();

        interactor.create(user);

        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> interactor.create(existingUser));

        assertEquals(user.getEmail(), exception.getMessage());
    }

}
