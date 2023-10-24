package io.victorvld.domain.usecases.controller;

import io.victorvld.domain.usecases.requester.Request;
import io.victorvld.domain.usecases.responder.Response;

public interface Controller {
    Response handle(Request request);
}
