package io.essentials.adapter.presenter.doubles;

import io.essentials.domain.usecases.responder.InteractorResponse;

import java.util.Map;

public class DummyInteractorResponse implements InteractorResponse {
    @Override
    public boolean isResultSuccessful() {
        return false;
    }

    @Override
    public Map<String, String> getResult() {
        return null;
    }

    @Override
    public Map<String, String> getErrors() {
        return null;
    }
}
