package io.essentials.adapter.controller;

import io.essentials.domain.usecases.controller.Controller;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;

public class SignUpController implements Controller {

    private final InputBoundary useCase;

    public SignUpController(InputBoundary useCase) {
        this.useCase = useCase;
    }

    @Override
    public Response handle(Request request) {
        return useCase.execute(request);
    }
}
