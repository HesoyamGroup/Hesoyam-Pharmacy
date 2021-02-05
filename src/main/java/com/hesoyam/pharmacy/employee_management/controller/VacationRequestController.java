package com.hesoyam.pharmacy.employee_management.controller;

import com.hesoyam.pharmacy.employee_management.dto.VacationRequestDTO;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.service.IVacationRequestService;
import com.hesoyam.pharmacy.user.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vacation-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationRequestController {

    @Autowired
    private IVacationRequestService vacationRequestService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(value = "/created")
    public ResponseEntity<List<VacationRequestDTO>> getCreatedVacationRequestsByAdministrator(@AuthenticationPrincipal Administrator administrator){
        List<VacationRequest> vacationRequests = vacationRequestService.getNewVacationRequestsByAdministrator(administrator);
        List<VacationRequestDTO> vacationRequestsDTO = new ArrayList<>();
        vacationRequests.forEach(vacationRequest -> vacationRequestsDTO.add(new VacationRequestDTO(vacationRequest)));

        return ResponseEntity.status(HttpStatus.OK).body(vacationRequestsDTO);
    }
    
}
