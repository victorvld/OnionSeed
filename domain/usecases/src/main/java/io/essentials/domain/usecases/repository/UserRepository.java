package io.essentials.domain.usecases.repository;

import io.essentials.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    User create(User user);

    Optional<User> findByEmail(String email);
}
