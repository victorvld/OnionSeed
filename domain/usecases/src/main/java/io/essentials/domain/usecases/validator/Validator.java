package io.essentials.domain.usecases.validator;

import io.essentials.domain.entities.Entity;

public interface Validator<T extends Entity> {

    void validate(T entity);
}
