package com.hesoyam.pharmacy.employee_management.service;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.user.model.Administrator;

import java.util.List;

public interface IVacationRequestService {
    List<VacationRequest> getNewVacationRequestsByAdministrator(Administrator administrator);
}
