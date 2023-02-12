package io.essentials.adapter.controller;

import io.essentials.domain.usecases.controller.Controller;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;

public class FocusUnitController implements Controller {

    private InputBoundary interactor;

    public FocusUnitController(InputBoundary interactor) {
        this.interactor = interactor;
    }

    @Override
    public Response handle(Request request) {
        return interactor.execute(request);
    }
}
