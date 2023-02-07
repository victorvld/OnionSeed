package io.essentials.domain.usecases.requester;

import io.essentials.domain.usecases.requester.Request;

public record LoginRequest(String username, String password) implements Request {
}
