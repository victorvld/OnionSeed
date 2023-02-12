package io.essentials.domain.usecases.repository;

import io.essentials.domain.entities.Pomodoro;
import io.essentials.domain.entities.User;

import java.util.Optional;

public interface PomodoroRepository {
    User create(User user);

    Optional<Pomodoro> save(Pomodoro pomodoro);

}
