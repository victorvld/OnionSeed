package io.essentials.domain.usecases.responder;

import io.essentials.domain.entities.User;
import io.essentials.domain.usecases.responder.Response;

public record SignUpResponse(User user) implements Response {
}
