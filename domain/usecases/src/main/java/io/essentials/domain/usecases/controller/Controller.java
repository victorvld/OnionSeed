package io.essentials.domain.usecases.controller;

import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;

public interface Controller {
    Response handle(Request request);
}
