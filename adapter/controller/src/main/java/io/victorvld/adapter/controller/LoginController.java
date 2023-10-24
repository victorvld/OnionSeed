package io.victorvld.adapter.controller;

import io.victorvld.domain.usecases.controller.Controller;
import io.victorvld.domain.usecases.interactor.InputBoundary;
import io.victorvld.domain.usecases.requester.Request;
import io.victorvld.domain.usecases.responder.Response;

public class LoginController implements Controller {

    InputBoundary useCase;
    public LoginController(InputBoundary useCase) {
        this.useCase = useCase;
    }

    @Override
     public Response handle(Request request){
         return useCase.execute(request);
     }
}
