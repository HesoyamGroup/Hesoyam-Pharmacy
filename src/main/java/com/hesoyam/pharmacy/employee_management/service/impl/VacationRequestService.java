package com.hesoyam.pharmacy.employee_management.service.impl;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import com.hesoyam.pharmacy.employee_management.repository.VacationRequestRepository;
import com.hesoyam.pharmacy.employee_management.service.IVacationRequestService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationRequestService implements IVacationRequestService {
    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<VacationRequest> getNewVacationRequestsByAdministrator(Administrator administrator){
        Administrator admin = administratorRepository.getOne(administrator.getId());

        List<VacationRequest> newRequests = vacationRequestRepository.findAllByStatus(VacationRequestStatus.CREATED);
        return newRequests.stream().filter(vacationRequest -> vacationRequest.isRequestedForPharmacy(admin.getPharmacy()) /*&& vacationRequest.isRecent()*/).collect(Collectors.toList());
    }
}
