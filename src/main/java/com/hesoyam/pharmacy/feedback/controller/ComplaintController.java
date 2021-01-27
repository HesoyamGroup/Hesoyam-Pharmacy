package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.feedback.DTO.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.service.IComplaintService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @PostMapping("/create-employee-complaint")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<EmployeeComplaint> createEmployeeComplaint(@RequestBody @Valid EmployeeComplaintCreateDTO employeeComplaintCreateDTO){
        EmployeeComplaint employeeComplaint;
        User currentLoggedUser = getCurrentLoggedUser();
        if(currentLoggedUser == null){
            //If for some reason, currentLoggedUser is not properly returned, return error.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        employeeComplaintCreateDTO.setPatientId(currentLoggedUser.getId());

        try {
            employeeComplaint = complaintService.createEmployeeComplaint(employeeComplaintCreateDTO);
        } catch (InvalidComplaintRequestException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeComplaint);
    }

    private User getCurrentLoggedUser(){
        try{
            return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (ClassCastException e){
            return null;
        }
    }
}
