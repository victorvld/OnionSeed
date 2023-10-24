package io.victorvld.domain.usecases.repository;

import io.victorvld.domain.entities.User;
import io.victorvld.domain.entities.UserSession;

import java.util.Optional;

public interface UserRepository {
    User create(User user);

    Optional<User> findByEmail(String email);

    boolean checkPassword(String username, String password);

    Optional<String> findSessionTokenByEmail(String email);

    UserSession createUserSession(String sessionToken, String token);
}
