package io.essentials.repository;

import io.essentials.domain.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository inMemoryDb;
    @BeforeEach
    public void beforeAll() {
        inMemoryDb = new InMemoryUserRepository();
    }

    @Test
    void createUserTest() {
        var user = User.builder()
                .id("id")
                .firstName("fName")
                .lastName("lName")
                .password("password")
                .email("example@email.io")
                .build();

        var createdUser = inMemoryDb.create(user);

        Assertions.assertTrue(createdUser.isEquals(user));
    }

    @Test
    void findByEmailExistingEmailTest() {
        String email = "example@email.io";
        var user = User.builder()
                .id("id")
                .firstName("fName")
                .lastName("lName")
                .password("password")
                .email(email)
                .build();

        inMemoryDb.create(user);

        Assertions.assertTrue(inMemoryDb.findByEmail(email).isPresent());
    }


    @Test
    void findByEmailNonExistingEmailTest() {
        String email = "exampleNonExisting@email.io";

        Assertions.assertFalse(inMemoryDb.findByEmail(email).isPresent());
    }

    @Test
    void whenUserIsLoggedThenFindSessionTokenByEmailReturnsPresent() {
        String email = "loggedUser@email.io";
        String token = "session01";

        inMemoryDb.createUserSession(email, token);

        Assertions.assertTrue(inMemoryDb.findSessionTokenByEmail(email).isPresent());
    }

    @Test
    void whenUserIsNotLoggedThenFindSessionTokenByEmailReturnsNotPresent() {
        String email = "NotLoggedUser@email.io";

        Assertions.assertFalse(inMemoryDb.findSessionTokenByEmail(email).isPresent());
    }

}
