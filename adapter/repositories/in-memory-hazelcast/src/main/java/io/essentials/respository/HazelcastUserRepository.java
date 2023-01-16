package io.essentials.respository;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.repository.UserRepository;

import java.util.Optional;

public class HazelcastUserRepository implements UserRepository {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
