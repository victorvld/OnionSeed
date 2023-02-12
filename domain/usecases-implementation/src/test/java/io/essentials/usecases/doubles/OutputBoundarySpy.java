package io.essentials.usecases.doubles;

import io.essentials.domain.usecases.presenter.OutputBoundary;
import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;

public class OutputBoundarySpy implements OutputBoundary {
    private boolean wasPresentMethodCalled;

    private InteractorResponse response;

    @Override
    public Response present(InteractorResponse response) {
        this.response = response;
        this.wasPresentMethodCalled = true;
        return null;
    }

    public boolean wasPresentMethodCalled() {
        return this.wasPresentMethodCalled;
    }

    public InteractorResponse getResponse() {
        return this.response;
    }
}
