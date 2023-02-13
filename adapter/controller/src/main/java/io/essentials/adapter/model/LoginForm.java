package io.essentials.adapter.model;

import io.essentials.domain.usecases.requester.Request;

public record LoginForm(String username, String password) implements Request {
}
