package com.hesoyam.pharmacy.employee_management.service.impl;

import com.hesoyam.pharmacy.employee_management.dto.VacationRequestDTO;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import com.hesoyam.pharmacy.employee_management.repository.VacationRequestRepository;
import com.hesoyam.pharmacy.employee_management.service.IVacationRequestService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationRequestService implements IVacationRequestService {
    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<VacationRequest> getNewVacationRequestsByAdministrator(User user){
        List<VacationRequest> newRequests = vacationRequestRepository.findAllByStatus(VacationRequestStatus.CREATED);

        if(user.getRoleEnum() == RoleEnum.ADMINISTRATOR) {
            Administrator administrator = administratorRepository.getOne(user.getId());
            return newRequests.stream().filter(vr -> vr.isRequestedForPharmacy(administrator.getPharmacy()) && vr.isPharmacistVacationRequest()).collect(Collectors.toList());
        } else if(user.getRoleEnum() == RoleEnum.SYS_ADMIN){
            return newRequests;
        }
        return new ArrayList<>();
    }

    @Override
    public VacationRequest reject(User user, VacationRequestDTO vacationRequest) {
        Administrator administrator = administratorRepository.getOne(user.getId());

        VacationRequest rejectingVacationRequest = vacationRequestRepository.getOne(vacationRequest.getId());
        rejectingVacationRequest.reject(vacationRequest.getRejectReason());
        VacationRequest rejectedVacationRequest = vacationRequestRepository.save(rejectingVacationRequest);

        return rejectedVacationRequest;
    }
}
