package io.essentials.adapter.view.login;

import io.essentials.adapter.presenter.response.ViewResponse;
import io.essentials.adapter.utilities.ViewTemplate;
import io.essentials.domain.usecases.responder.InteractorResponse;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.domain.usecases.view.View;

public class LoginView implements View {
    @Override
    public Response generateView(InteractorResponse response) {
        ViewResponse viewResponse;
        if (response != null
                && response.getResult() != null
                && response.getErrors() != null
                && !response.getResult().isEmpty()
                && (response.getResult().containsKey("sessionToken"))
        ) {
            ViewTemplate template = ViewTemplate.create("templates/home_user_page.html");
            viewResponse = new ViewResponse(200, null, template.getContent());
        } else if ((response != null
                && response.getResult() != null
                && response.getErrors() != null
                && !response.getErrors().isEmpty())) {

            if (response.getErrors().containsKey("incorrectPasswordOrNonExistingAccount")) {
                // TODO: 2/10/23 implement this logic doing TDD
                ViewTemplate template = ViewTemplate.create("templates/login_page.html");
                viewResponse = new ViewResponse(200, null, template.getContent());
            } else {
                ViewTemplate template = ViewTemplate.create("templates/login_page.html");
                viewResponse = new ViewResponse(200, null, template.getContent());
            }

        } else {
            ViewTemplate template = ViewTemplate.create("templates/500_error_page.html");
            viewResponse = new ViewResponse(500, null, template.getContent());
        }
        return viewResponse;
    }
}
