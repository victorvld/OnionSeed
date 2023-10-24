package io.victorvld.domain.usecases.interactor;

import io.victorvld.domain.usecases.requester.Request;
import io.victorvld.domain.usecases.responder.Response;

public interface InputBoundary {

    Response execute(Request request);
}
