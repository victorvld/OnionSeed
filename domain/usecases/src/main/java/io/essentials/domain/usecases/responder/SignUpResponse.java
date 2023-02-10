package io.essentials.domain.usecases.responder;

import io.essentials.domain.entities.User;

public record SignUpResponse(User user) implements Response {
}
