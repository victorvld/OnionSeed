package io.essentials.usecases.focusUnit;

import io.essentials.domain.usecases.responder.Response;
import io.essentials.usecases.focusUnit.request.FocusUnitRequest;
import io.essentials.usecases.focusUnit.response.FocusUnitResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FocusUnitInteractorTest {
    @DisplayName("FocusUnitInteractorBindingTest")
    @Test
    void testFocusUnitInteractorBindingTest() {
        FocusUnitResponse expectedResponse = new FocusUnitResponse(true);
        FocusUnitRequest request = new FocusUnitRequest(
                "startAt",
                "finishAt",
                "breakStartAt",
                "breakFinishAt",
                0,
                0,
                0
        );
//        FocusUnitInteractor interactor = new FocusUnitInteractor();
//
//        Assertions.assertEquals(expectedResponse.createdRecord(), ( interactor.execute(request)).createdRecord());

    }
}