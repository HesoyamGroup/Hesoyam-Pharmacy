package com.hesoyam.pharmacy.employee_management.controller;

import com.hesoyam.pharmacy.employee_management.dto.VacationRequestDTO;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import com.hesoyam.pharmacy.employee_management.service.IVacationRequestService;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vacation-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationRequestController {

    @Autowired
    private IVacationRequestService vacationRequestService;

    @Autowired
    private IEmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SYS_ADMIN')")
    @GetMapping(value = "/created")
    public ResponseEntity<List<VacationRequestDTO>> getCreatedVacationRequestsByAdministrator(@AuthenticationPrincipal User user){
        List<VacationRequest> vacationRequests = vacationRequestService.getNewVacationRequestsByAdministrator(user);
        List<VacationRequestDTO> vacationRequestsDTO = new ArrayList<>();
        vacationRequests.forEach(vacationRequest -> vacationRequestsDTO.add(new VacationRequestDTO(vacationRequest)));

        return ResponseEntity.status(HttpStatus.OK).body(vacationRequestsDTO);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SYS_ADMIN')")
    @PutMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VacationRequestDTO> reject(@RequestBody VacationRequestDTO vacationRequest, @AuthenticationPrincipal User user){
        try{
            VacationRequest rejectedVacationRequest = vacationRequestService.reject(user, vacationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new VacationRequestDTO(rejectedVacationRequest));
        } catch (IllegalStateException | ObjectOptimisticLockingFailureException e){
            //conflict (vacation request is already rejected or accepted)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SYS_ADMIN')")
    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<VacationRequestDTO> accept(@PathVariable Long id, @AuthenticationPrincipal User user){
        try{
            VacationRequest acceptedVacationRequest = vacationRequestService.accept(user, id);
            return ResponseEntity.status(HttpStatus.OK).body(new VacationRequestDTO(acceptedVacationRequest));
        } catch (IllegalStateException | ObjectOptimisticLockingFailureException e){
            //conflict (vacation request is already rejected or accepted)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PreAuthorize("hasAnyRole('PHARMACIST', 'DERMATOLOGIST')")
    @PostMapping(value="/create-request")
    public ResponseEntity<String> create(@AuthenticationPrincipal User user, @RequestBody DateTimeRange dateTimeRange){
        VacationRequest vacationRequest = new VacationRequest();
        if(dateTimeRange.getFrom().isBefore(dateTimeRange.getTo())) {
            vacationRequest.setEmployee(employeeService.getOne(user.getId()));
            vacationRequest.setDateTimeRange(dateTimeRange);
            vacationRequest.setStatus(VacationRequestStatus.CREATED);
            VacationRequest newRequest = vacationRequestService.create(vacationRequest);
            if (newRequest != null) {
                return new ResponseEntity<>("Successfully created request!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to create request!", HttpStatus.OK);
    }
}
