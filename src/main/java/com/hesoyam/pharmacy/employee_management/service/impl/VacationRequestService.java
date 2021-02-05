package com.hesoyam.pharmacy.employee_management.service.impl;

import com.hesoyam.pharmacy.employee_management.dto.VacationRequestDTO;
import com.hesoyam.pharmacy.employee_management.events.OnVacationRequestRejectionEvent;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import com.hesoyam.pharmacy.employee_management.repository.VacationRequestRepository;
import com.hesoyam.pharmacy.employee_management.service.IVacationRequestService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<VacationRequest> getNewVacationRequestsByAdministrator(User user){
        List<VacationRequest> newRequests = vacationRequestRepository.findAllByStatus(VacationRequestStatus.CREATED);

        if(user.getRoleEnum() == RoleEnum.ADMINISTRATOR) {
            Administrator administrator = administratorRepository.getOne(user.getId());
            return newRequests.stream().filter(vr -> vr.isRequestedForPharmacy(administrator.getPharmacy()) && vr.isPharmacistVacationRequest()).collect(Collectors.toList());
        } else if(user.getRoleEnum() == RoleEnum.SYS_ADMIN){
            return newRequests.stream().filter(VacationRequest::isDermatologistVacationRequest).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public VacationRequest reject(User user, VacationRequestDTO vacationRequest) throws IllegalAccessException {
        VacationRequest rejectingVacationRequest = vacationRequestRepository.getOne(vacationRequest.getId());

        if(user.getRoleEnum() == RoleEnum.ADMINISTRATOR){
            Administrator administrator = administratorRepository.getOne(user.getId());
            if(rejectingVacationRequest.isPharmacistVacationRequest() && rejectingVacationRequest.isRequestedForPharmacy(administrator.getPharmacy()))
                rejectingVacationRequest.reject(vacationRequest.getRejectReason());
            else
                throw new IllegalAccessException(String.format("Administrator '%s' doesn't have a permission to reject this vacation request [id: '%s']", administrator.getId(), rejectingVacationRequest.getId()));
        }
        else if(user.getRoleEnum() == RoleEnum.SYS_ADMIN){
            if(rejectingVacationRequest.isDermatologistVacationRequest())
                rejectingVacationRequest.reject(vacationRequest.getRejectReason());
            else
                throw new IllegalAccessException(String.format("System administrator doesn't have a permission to reject pharmacist vacation request [id: '%s']", rejectingVacationRequest.getId()));
        }

        VacationRequest rejectedVacationRequest = vacationRequestRepository.save(rejectingVacationRequest);
        applicationEventPublisher.publishEvent(new OnVacationRequestRejectionEvent(rejectedVacationRequest));
        return rejectedVacationRequest;
    }

    @Override
    public VacationRequest accept(User user, Long id) throws IllegalAccessException {
        VacationRequest acceptingVacationRequest = vacationRequestRepository.getOne(id);

        if(user.getRoleEnum() == RoleEnum.ADMINISTRATOR){
            Administrator administrator = administratorRepository.getOne(user.getId());
            if(acceptingVacationRequest.isPharmacistVacationRequest() && acceptingVacationRequest.isRequestedForPharmacy(administrator.getPharmacy()))
                acceptingVacationRequest.accept();
            else
                throw new IllegalAccessException(String.format("Administrator '%s' doesn't have a permission to accept this vacation request [id: '%s']", administrator.getId(), acceptingVacationRequest.getId()));
        }
        else if(user.getRoleEnum() == RoleEnum.SYS_ADMIN){
            if(acceptingVacationRequest.isDermatologistVacationRequest())
                acceptingVacationRequest.accept();
            else
                throw new IllegalAccessException(String.format("System administrator doesn't have a permission to accept pharmacist vacation request [id: '%s']", acceptingVacationRequest.getId()));
        }

        return vacationRequestRepository.save(acceptingVacationRequest);
    }
}
