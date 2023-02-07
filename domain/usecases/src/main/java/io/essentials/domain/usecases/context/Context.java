package io.essentials.domain.usecases.context;

import io.essentials.domain.usecases.encoder.PasswordEncoder;
import io.essentials.domain.usecases.generator.IdGenerator;
import io.essentials.domain.usecases.repository.UserRepository;

public final class Context {

    public static UserRepository repository;
    public static PasswordEncoder passwordEncoder;
    public static IdGenerator idGenerator;
    public static IdGenerator sessionIdGenerator;

}
