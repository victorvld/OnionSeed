package io.essentials.adapter.presenter.login;

import io.essentials.domain.usecases.presenter.OutputBoundary;
import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.domain.usecases.view.View;

public class LoginPresenter implements OutputBoundary {
    private View view;

    public LoginPresenter(View view) {
        this.view = view;
    }

    @Override
    public Response present(InteractorResponse response) {
        return view.generateView(response);
    }
}
