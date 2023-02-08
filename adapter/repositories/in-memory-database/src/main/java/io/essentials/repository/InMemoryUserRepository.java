package io.essentials.repository;

import io.essentials.domain.entities.User;
import io.essentials.domain.entities.UserSession;
import io.essentials.domain.usecases.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> inMemoryDb = new HashMap<>();
    private final Map<String, String> inMemoryUserSessions = new HashMap<>();

    @Override
    public User create(User user) {
        inMemoryDb.put(user.getId(), user);
        return inMemoryDb.get(user.getId());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        for (User user : inMemoryDb.values()) {
            if (user.getEmail().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isUserRegistered(String username) {
        return findByUsername(username).isPresent();
    }

    @Override
    public boolean checkPassword(String username, String password) {
        var result = false;
        var user = findByUsername(username);
        if (user.isPresent()) {
            result = user.get().getPassword().equals(password);
        }
        return result;
    }

    @Override
    public Optional<String> findSessionTokenByUsername(String username) {
        if (inMemoryUserSessions.containsKey(username)) {
            return Optional.of(inMemoryUserSessions.get(username));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UserSession createUserSession(String email, String token, String expiresAt) {
        inMemoryUserSessions.put(email, token);
        return new UserSession(token, expiresAt);
    }
}
