package io.essentials.repository;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.repository.UserRepository;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> inMemoryDb = new HashMap<>();

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
}
