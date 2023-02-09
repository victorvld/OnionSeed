package io.essentials.usecases.doubles;

import io.essentials.domain.usecases.presenter.OutputBoundary;
import io.essentials.domain.usecases.responder.Response;

public class OutputBoundarySpy implements OutputBoundary {
    private boolean wasPresentMethodCalled;

    private Response response;

    @Override
    public Response present(Response response) {
        this.response = response;
        this.wasPresentMethodCalled = true;
        return null;
    }

    public boolean wasPresentMethodCalled() {
        return this.wasPresentMethodCalled;
    }

    public Response getResponse() {
        return this.response;
    }
}
