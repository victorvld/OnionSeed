package io.essentials.usecases.login.response;

import io.essentials.domain.usecases.responder.Response;

public record LoginResponse(boolean success, String sessionToken) implements Response {
}
