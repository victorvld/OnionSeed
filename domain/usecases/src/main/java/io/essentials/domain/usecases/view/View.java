package io.essentials.domain.usecases.view;

import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;

public interface View {

    Response generateView(InteractorResponse response);
}
