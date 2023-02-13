package io.essentials.adapter.controller.doubles;

import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;

public class InputBoundarySpy implements InputBoundary {

    private boolean executeMethodWasCalled;
    private Request request;

    @Override
    public Response execute(Request request) {
        this.executeMethodWasCalled = true;
        this.request = request;
        return null;
    }

    public boolean wasExecuteMethodCalled() {

        return this.executeMethodWasCalled;
    }

    public Request getRequest() {
        return request;
    }
}
