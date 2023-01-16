package io.essentials.domain.usecases.context;

import io.essentials.domain.usecases.encoder.PasswordEncoder;
import io.essentials.domain.usecases.generator.IdGenerator;
import io.essentials.domain.usecases.repository.UserRepository;
import io.essentials.domain.usecases.validator.Validator;

public class Context {

    public static UserRepository repository;
    public static Validator userValidator;

    public static PasswordEncoder passwordEncoder;

    public static IdGenerator idGenerator;
}
