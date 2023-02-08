package io.essentials.respository;

import io.essentials.domain.entities.User;
import io.essentials.domain.entities.UserSession;
import io.essentials.domain.usecases.repository.UserRepository;

import java.util.Optional;

public class HazelcastUserRepository implements UserRepository {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean isUserRegistered(String username) {
        return false;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public Optional<String> findSessionTokenByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public UserSession createUserSession(String sessionToken, String token, String expiresAt) {
        return null;
    }
}
