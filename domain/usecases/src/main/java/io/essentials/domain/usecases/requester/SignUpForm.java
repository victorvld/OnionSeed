package io.essentials.domain.usecases.requester;

import io.essentials.domain.usecases.requester.Request;

public record SignUpForm(String email, String password, String firstName, String lastName) implements Request {
}
