package io.essentials.usecases.focusUnit.interactor;

import io.essentials.domain.entities.Entity;
import io.essentials.domain.entities.Pomodoro;
import io.essentials.domain.usecases.context.Context;
import io.essentials.domain.usecases.interactor.InputBoundary;
import io.essentials.domain.usecases.responder.Response;
import io.essentials.usecases.focusUnit.request.FocusUnitRequest;
import io.essentials.usecases.focusUnit.response.FocusUnitResponse;

/*public class FocusUnitInteractor implements InputBoundary<FocusUnitRequest> {

   @Override
    public FocusUnitResponse execute(FocusUnitRequest request) {
        Pomodoro pomodoro = Pomodoro.builder()
                .id(Context.idGenerator.generate()).build();

        Context.focusUnitRepository.save(pomodoro)
        return null;
    }
}*/
