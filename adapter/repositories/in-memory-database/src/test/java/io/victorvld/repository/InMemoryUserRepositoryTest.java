package io.victorvld.repository;

import io.victorvld.domain.entities.User;
import org.junit.jupiter.api.*;
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
