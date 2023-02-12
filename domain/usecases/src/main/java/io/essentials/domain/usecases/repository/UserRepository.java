package io.essentials.domain.usecases.repository;

import io.essentials.domain.entities.User;
import io.essentials.domain.entities.UserSession;

import java.util.Optional;

public interface UserRepository {
    User create(User user);

    Optional<User> findByUsername(String username);

    boolean isUserRegistered(String username);

    boolean checkPassword(String username, String password);

    Optional<String> findSessionTokenByUsername(String username);

    UserSession createUserSession(String sessionToken, String token, String expiresAt);
}
