package io.essentials.adapter.presenter.doubles;

import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.domain.usecases.view.View;

public class ViewSpy implements View {

    private boolean wasGenerateViewMethodCalled;

    @Override
    public Response generateView(InteractorResponse response) {
        wasGenerateViewMethodCalled = true;
        return null;
    }

    public boolean wasGenerateViewMethodCalled() {
        return this.wasGenerateViewMethodCalled;
    }
}
