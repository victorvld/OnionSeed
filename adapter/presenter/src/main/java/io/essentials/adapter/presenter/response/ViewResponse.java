package io.essentials.adapter.presenter.response;

import io.essentials.domain.usecases.responder.Response;

import java.util.List;
import java.util.Map;

public record ViewResponse(int statusCode, Map<String, List<String>> headers, String content) implements Response {
}

