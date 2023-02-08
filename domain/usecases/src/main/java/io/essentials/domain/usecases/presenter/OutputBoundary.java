package io.essentials.domain.usecases.presenter;

import io.essentials.domain.usecases.responder.Response;

public interface OutputBoundary {

    String present(Response response);
}
