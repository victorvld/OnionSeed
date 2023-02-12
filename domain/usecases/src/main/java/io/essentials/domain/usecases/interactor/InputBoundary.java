package io.essentials.domain.usecases.interactor;

import io.essentials.domain.usecases.requester.Request;
import io.essentials.domain.usecases.responder.Response;

public interface InputBoundary<T extends Request> {

    Response execute(T request);
}
