package io.victorvld.adapter.model;

import io.victorvld.domain.usecases.requester.Request;

public record LoginForm(String username, String password) implements Request {
}
