package io.essentials.domain.usecases.responder;

import io.essentials.domain.usecases.responder.Response;

import java.util.Optional;

public record LoginResponse(boolean success, Optional<String> sessionToken, Optional<String> reason) implements Response {
}
