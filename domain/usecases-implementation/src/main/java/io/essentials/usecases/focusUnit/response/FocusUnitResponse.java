package io.essentials.usecases.focusUnit.response;

import io.essentials.domain.usecases.responder.Response;

import java.util.UUID;

public record FocusUnitResponse(Boolean createdRecord) implements Response {
}
