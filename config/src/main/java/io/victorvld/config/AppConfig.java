package io.victorvld.config;

import io.victorvld.adapter.encoder.Sha256Encoder;
import io.victorvld.adapter.generator.UuidGenerator;
import io.victorvld.adapter.validator.SimpleUserValidator;
import io.victorvld.domain.entities.User;
import io.victorvld.domain.usecases.context.Context;
import io.victorvld.repository.InMemoryUserRepository;

public class AppConfig {
    public static void setupUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.sessionIdGenerator = new UuidGenerator();
        Context.userValidator = new SimpleUserValidator();
    }

    public static void setupTestUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.userValidator = new SimpleUserValidator();

        var user = User.builder()
                .id("1")
                .email("registeredUser@email.io")
                .firstName("registered")
                .lastName("user")
                .password("password")
                .build();

        var user2 = User.builder()
                .id("2")
                .email("varranz.devs@outlook.com")
                .firstName("Victor")
                .lastName("Arranz")
                .password("123")
                .build();

        Context.repository.create(user);
        Context.repository.create(user2);
    }
}
