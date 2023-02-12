package io.essentials.adapter.controller;

import io.essentials.adapter.controller.doubles.InputBoundarySpy;
import io.essentials.domain.usecases.requester.Request;
import io.essentials.usecases.focusUnit.request.FocusUnitRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FocusUnitControllerTest {
    @DisplayName("FocusUnitControllerInteractorBindingTest")
    @Test
    void testFocusUnitControllerInteractorBinding() {

        InputBoundarySpy interactor = new InputBoundarySpy();

        FocusUnitController controller = new FocusUnitController(interactor);
        Request request = new FocusUnitRequest(
                "startAt",
                "finishAt",
                "breakStartAt",
                "breakFinishAt",
                0,
                0,
                0
        );
        controller.handle(request);
        Assertions.assertTrue(interactor.wasExecuteMethodCalled());
    }


}
