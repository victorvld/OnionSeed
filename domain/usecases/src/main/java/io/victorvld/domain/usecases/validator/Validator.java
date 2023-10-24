package io.victorvld.domain.usecases.validator;

import io.victorvld.domain.entities.Entity;

public interface Validator<T extends Entity> {

    void validate(T entity);
}
