package io.essentials.domain.usecases.presenter;

import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;

public interface OutputBoundary {

    Response present(InteractorResponse response);
}
