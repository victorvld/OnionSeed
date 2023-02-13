package io.essentials.config;

import io.essentials.adapter.encoder.Sha256Encoder;
import io.essentials.adapter.generator.UuidGenerator;
import io.essentials.domain.usecases.context.Context;
import io.essentials.domain.usecases.requester.SignUpForm;
import io.essentials.repository.InMemoryUserRepository;
import io.essentials.usecases.signup.interactor.SignUpInteractor;

public class AppConfig {
    public static void setupUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.sessionIdGenerator = new UuidGenerator();
    }

    public static void setupTestUserContext() {
        Context.repository = new InMemoryUserRepository();
        Context.passwordEncoder = new Sha256Encoder();
        Context.idGenerator = new UuidGenerator();
        Context.sessionIdGenerator = new UuidGenerator();

        var newUser = new SignUpForm("registeredUser@email.io", "password", "user", "registered");
        var newUser2 = new SignUpForm("varranz.devs@outlook.com", "password", "Victor", "Arranz");

        new SignUpInteractor().execute(newUser);
        new SignUpInteractor().execute(newUser2);
    }
}
