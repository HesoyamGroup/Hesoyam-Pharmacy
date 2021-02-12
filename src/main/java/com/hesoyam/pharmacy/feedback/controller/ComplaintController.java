package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.feedback.dto.*;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidReplyRequest;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.model.PharmacyComplaint;
import com.hesoyam.pharmacy.feedback.model.Reply;
import com.hesoyam.pharmacy.feedback.service.IComplaintService;
import com.hesoyam.pharmacy.feedback.service.IReplyService;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.SysAdmin;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;
    @Autowired
    private IReplyService replyService;

    @GetMapping("/unanswered")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<List<ComplaintDataDTO>> getAllUnanswered(){
        return ResponseEntity.ok(complaintService.getAllUnanswered());
    }


    @PostMapping("/reply")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Reply> reply(@RequestBody @Valid ReplyDTO replyDTO){
        SysAdmin sysAdmin = getSysAdmin();
        if(sysAdmin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        replyDTO.setSysAdmin(sysAdmin);
        try {
            return ResponseEntity.ok(replyService.reply(replyDTO));
        } catch (InvalidReplyRequest | ObjectOptimisticLockingFailureException invalidReplyRequest) {
            return ResponseEntity.badRequest().build();
        }
    }

    private SysAdmin getSysAdmin(){
        SysAdmin sysAdmin;
        try{
            sysAdmin = (SysAdmin) getCurrentLoggedUser();
            return sysAdmin;
        }catch (ClassCastException e){
            return null;
        }
    }

    private Patient getPatient(){
        Patient patient;
        try{
            patient = (Patient) getCurrentLoggedUser();
            return patient;
        }catch (ClassCastException e){
            return null;
        }
    }

    @PostMapping("/create-employee-complaint")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<ComplaintCreatedResponseDTO> createEmployeeComplaint(@RequestBody @Valid EmployeeComplaintCreateDTO employeeComplaintCreateDTO){
        EmployeeComplaint employeeComplaint;
        Patient currentLoggedPatient = getPatient();
        if(currentLoggedPatient == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        employeeComplaintCreateDTO.setPatient(currentLoggedPatient);

        try {
            employeeComplaint = complaintService.createEmployeeComplaint(employeeComplaintCreateDTO);
        } catch (InvalidComplaintRequestException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ComplaintCreatedResponseDTO(employeeComplaint.getId(), employeeComplaint.getEntityName(), employeeComplaint.getBody()));
    }

    @PostMapping("/create-pharmacy-complaint")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<ComplaintCreatedResponseDTO> createPharmacyComplaint(@RequestBody @Valid PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO){
        PharmacyComplaint pharmacyComplaint;
        Patient currentLoggedPatient = getPatient();
        if(currentLoggedPatient == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        pharmacyComplaintCreateDTO.setPatient(currentLoggedPatient);

        try{
            pharmacyComplaint = complaintService.createPharmacyComplaint(pharmacyComplaintCreateDTO);
        }catch(InvalidComplaintRequestException invalidComplaintRequestException){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ComplaintCreatedResponseDTO(pharmacyComplaint.getId(), pharmacyComplaint.getEntityName(), pharmacyComplaint.getBody()));
    }

    private User getCurrentLoggedUser(){
        try{
            return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (ClassCastException e){
            return null;
        }
    }
}
