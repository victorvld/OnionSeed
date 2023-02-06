package io.essentials.domain.usecases.context;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.encoder.PasswordEncoder;
import io.essentials.domain.usecases.generator.IdGenerator;
import io.essentials.domain.usecases.repository.UserRepository;
import io.essentials.domain.usecases.validator.Validator;

public final class Context {

    public static UserRepository repository;
    public static Validator<User> userValidator;
    public static PasswordEncoder passwordEncoder;
    public static IdGenerator idGenerator;
    public static IdGenerator sessionIdGenerator;
}
