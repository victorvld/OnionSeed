package io.essentials.usecases.login.response;

import io.essentials.domain.usecases.responder.Response;

public record LoginResponse(java.util.Map<String, String> result, java.util.Map<String, String> errors) implements Response {
}
