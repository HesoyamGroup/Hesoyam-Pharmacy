package com.hesoyam.pharmacy.employee_management.events;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import org.springframework.context.ApplicationEvent;

public class OnVacationRequestRejectionEvent extends ApplicationEvent {
    private final VacationRequest vacationRequest;

    public OnVacationRequestRejectionEvent(VacationRequest vacationRequest) {
        super(vacationRequest);
        this.vacationRequest = vacationRequest;
    }

    public VacationRequest getVacationRequest() {
        return vacationRequest;
    }
}
