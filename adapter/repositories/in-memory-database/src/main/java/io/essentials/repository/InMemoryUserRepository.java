package io.essentials.repository;

import io.essentials.domain.entities.User;
import io.essentials.domain.entities.UserSession;
import io.essentials.domain.usecases.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> inMemoryDb = new HashMap<>();
    private final Map<String, String> inMemoryUserSessions = new HashMap<>();

    @Override
    public User create(User user) {
        inMemoryDb.put(user.getId(), user);
        return inMemoryDb.get(user.getId());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (User user : inMemoryDb.values()) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean checkPassword(String username, String password) {
        var result = false;
        var user = findByEmail(username);
        if (user.isPresent()) {
            result = user.get().getPassword().equals(password);
        }
        return result;
    }

    @Override
    public Optional<String> findSessionTokenByEmail(String email) {
        if (inMemoryUserSessions.containsKey(email)) {
            return Optional.of(inMemoryUserSessions.get(email));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UserSession createUserSession(String email, String token) {
        // TODO: 1/26/23 Session expiresAt is a configurable value.
        //  Implement this functionality in a separate story.
        inMemoryUserSessions.put(email, token);
        var fixedSessionDuration = Duration.ofMinutes(30L);
        return new UserSession(token, LocalDateTime.now().plus(fixedSessionDuration));
    }
}
