package io.essentials.adapter.controller;

import io.essentials.domain.usecases.requester.Request;

public record FocusUnitRequest(String startAt,
                               String finishAt,
                               String breakStartAt,
                               String breakFinishAt,
                               Integer internalInterruptions,
                               Integer externalInterruptions,
                               Integer numPauses
) implements Request {

}


