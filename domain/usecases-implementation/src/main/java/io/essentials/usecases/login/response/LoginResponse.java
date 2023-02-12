package io.essentials.usecases.login.response;

import io.essentials.domain.usecases.responder.InteractorResponse;

import java.util.Map;

public record LoginResponse(java.util.Map<String, String> result,
                            java.util.Map<String, String> errors) implements InteractorResponse {
    @Override
    public boolean isResultSuccessful() {
        return errors.isEmpty();
    }

    @Override
    public Map<String, String> getResult() {
        return result;
    }

    @Override
    public Map<String, String> getErrors() {
        return errors;
    }
}
