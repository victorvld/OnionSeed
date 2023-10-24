package io.victorvld.domain.usecases.context;

import io.victorvld.domain.entities.User;
import io.victorvld.domain.usecases.encoder.PasswordEncoder;
import io.victorvld.domain.usecases.generator.IdGenerator;
import io.victorvld.domain.usecases.repository.UserRepository;
import io.victorvld.domain.usecases.validator.Validator;

public final class Context {

    public static UserRepository repository;
    public static Validator<User> userValidator;
    public static PasswordEncoder passwordEncoder;
    public static IdGenerator idGenerator;
    public static IdGenerator sessionIdGenerator;
}
