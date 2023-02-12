package io.essentials.usecases.focusUnit.request;

import io.essentials.domain.usecases.requester.Request;

import java.util.UUID;

public record FocusUnitRequest(String startAt,
                               String finishAt,
                               String breakStartAt,
                               String breakFinishAt,
                               Integer internalInterruptions,
                               Integer externalInterruptions,
                               Integer numPauses
) implements Request {

}
