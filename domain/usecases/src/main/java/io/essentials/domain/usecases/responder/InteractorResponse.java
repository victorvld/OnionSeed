package io.essentials.domain.usecases.responder;

import java.util.Map;

public interface InteractorResponse {
    boolean isResultSuccessful();
    Map<String, String> getResult();
    Map<String, String> getErrors();
}
