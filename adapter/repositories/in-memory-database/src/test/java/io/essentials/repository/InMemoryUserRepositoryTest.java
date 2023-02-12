package io.essentials.repository;

import io.essentials.domain.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

        Assertions.assertTrue(inMemoryDb.isUserRegistered(email));
    }


    @Test
    void findByEmailNonExistingEmailTest() {
        String email = "exampleNonExisting@email.io";

        Assertions.assertFalse(inMemoryDb.isUserRegistered(email));
    }

    @Test
    void whenUserIsLoggedThenFindSessionTokenByEmailReturnsPresent() {
        String email = "loggedUser@email.io";
        String token = "session01";
        String expiresAt = "1234";

        inMemoryDb.createUserSession(email, token, expiresAt);

        Assertions.assertTrue(inMemoryDb.findSessionTokenByUsername(email).isPresent());
    }

    @Test
    void whenUserIsNotLoggedThenFindSessionTokenByEmailReturnsNotPresent() {
        String email = "NotLoggedUser@email.io";

        Assertions.assertFalse(inMemoryDb.findSessionTokenByUsername(email).isPresent());
    }

    @Nested
    @DisplayName("user registered")
    class FindMethods {

        private String username;

        @BeforeEach
        void setUp() {
            username = "email";
            var user = User.builder()
                    .password("rightPassword")
                    .email(username)
                    .build();

            inMemoryDb.create(user);
        }

        private static Stream<Arguments> loginCasesProvider() {
            return Stream.of(
                    Arguments.of("rightPassword", true),
                    Arguments.of("wrongPassword", false)
            );
        }

        @ParameterizedTest
        @MethodSource("loginCasesProvider")
        void checkUserPassword(String password, boolean expectedResult) {

            Assertions.assertEquals(inMemoryDb.checkPassword(this.username, password), expectedResult);
        }
    }

}
