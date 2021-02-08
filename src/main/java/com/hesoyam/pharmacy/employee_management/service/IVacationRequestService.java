package com.hesoyam.pharmacy.employee_management.service;

import com.hesoyam.pharmacy.employee_management.dto.VacationRequestDTO;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IVacationRequestService {
    List<VacationRequest> getNewVacationRequestsByAdministrator(User user);

    VacationRequest reject(User user, VacationRequestDTO vacationRequest) throws IllegalAccessException;

    VacationRequest accept(User user, Long id) throws IllegalAccessException;
}
