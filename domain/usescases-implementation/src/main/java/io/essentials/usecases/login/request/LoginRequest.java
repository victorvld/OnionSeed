package io.essentials.usecases.login.request;

import io.essentials.domain.usecases.requester.Request;

public record LoginRequest(String username, String password) implements Request {
}
