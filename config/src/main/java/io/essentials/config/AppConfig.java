package io.essentials.config;

import io.essentials.adapter.encoder.Sha256Encoder;
import io.essentials.adapter.generator.UuidGenerator;
import io.essentials.adapter.validator.SimpleUserValidator;
import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.context.Context;
import io.essentials.repository.InMemoryUserRepository;

public class AppConfig {

    // TODO: 1/21/23 Explore if this dependency injection, can be done using spring features like xml files, etc.
    public static void setupUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.userValidator = new SimpleUserValidator();
    }

    public static void setupTestUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.userValidator = new SimpleUserValidator();

        Context.repository.create(User.builder().email("Fulanito@e.io").firstName("Fulano").lastName("Armado").password("123").build());
    }
}
