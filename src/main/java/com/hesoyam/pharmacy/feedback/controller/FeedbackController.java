package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.feedback.dto.EmployeeFeedbackDTO;
import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import com.hesoyam.pharmacy.feedback.service.IEmployeeFeedbackService;
import com.hesoyam.pharmacy.feedback.service.IFeedbackService;
import com.hesoyam.pharmacy.user.exceptions.EmployeeNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {

    @Autowired
    private ICheckUpService checkUpService;

    @Autowired
    private ICounselingService counselingService;

    @Autowired
    private IEmployeeFeedbackService employeeFeedbackService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPatientService patientService;

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/checkups")
    public ResponseEntity<List<EmployeeFeedbackDTO>> getPastCheckups(@AuthenticationPrincipal User user){

        List<CheckUp> pastCheckups = checkUpService.getAllCompletedCheckupsByPatient(user.getId());

        List<EmployeeFeedbackDTO> employeeFeedbackDTOList = new ArrayList<>();
        if(!pastCheckups.isEmpty()){
            for(CheckUp c: pastCheckups){
                if(!isEmployeeInList(c.getDermatologist().getId(), employeeFeedbackDTOList)){
                    employeeFeedbackDTOList.add(new EmployeeFeedbackDTO(c));
                }
            }
        }

        return ResponseEntity.ok().body(employeeFeedbackDTOList);
    }

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/counselings")
    public ResponseEntity<List<EmployeeFeedbackDTO>> getPastCounselings(@AuthenticationPrincipal User user){

        List<Counseling> pastCounselings = counselingService.getAllCompletedCounselingsByPatient(user.getId());

        List<EmployeeFeedbackDTO> employeeFeedbackDTOList = new ArrayList<>();
        if(!pastCounselings.isEmpty()){
            for(Counseling c: pastCounselings){
                if(!isEmployeeInList(c.getPharmacist().getId(), employeeFeedbackDTOList)){
                    employeeFeedbackDTOList.add(new EmployeeFeedbackDTO(c));
                }
            }
        }

        return ResponseEntity.ok().body(employeeFeedbackDTOList);

    }

    private boolean isEmployeeInList(Long id, List<EmployeeFeedbackDTO> employeeFeedbackDTOList){
        for(EmployeeFeedbackDTO d: employeeFeedbackDTOList){
            if(d.getEmployeeId() == id)
                return true;
        }
        return false;
    }

    @Secured("ROLE_PATIENT")
    @PostMapping(value = "/employee")
    public ResponseEntity<Double> dermatologistFeedback(@RequestBody EmployeeFeedbackDTO employeeFeedbackDTO, @AuthenticationPrincipal User user) {

        List<EmployeeFeedback> employeeFeedbacks = employeeFeedbackService.findByEmployeeId(employeeFeedbackDTO.getEmployeeId());

        EmployeeFeedback employeeFeedback = findByPatientId(user.getId(), employeeFeedbacks);
        if(employeeFeedback != null){
            employeeFeedback.setRating(employeeFeedbackDTO.getYourRating());
            employeeFeedback.setComment(employeeFeedbackDTO.getYourComment());

            employeeFeedbackService.update(employeeFeedback);
        }
        else{
            try {
                employeeFeedback = new EmployeeFeedback();
                employeeFeedback.setComment(employeeFeedbackDTO.getYourComment());
                employeeFeedback.setRating(employeeFeedbackDTO.getYourRating());
                employeeFeedback.setEmployee(employeeService.getOne(employeeFeedbackDTO.getEmployeeId()));
                employeeFeedback.setPatient(patientService.getById(user.getId()));

                employeeFeedback = employeeFeedbackService.create(employeeFeedback);
            }catch (PatientNotFoundException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-2.0);
            }
        }
        double newRating = employeeFeedbackService.calculateEmployeeRating(employeeFeedbackDTO.getEmployeeId());
        try{
            employeeService.updateRating(employeeFeedbackDTO.getEmployeeId(), newRating);
        }catch (EmployeeNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1.0);
        }

        return ResponseEntity.ok().body(newRating);
    }

    private EmployeeFeedback findByPatientId(Long id, List<EmployeeFeedback> employeeFeedbacks){
        for(EmployeeFeedback ef: employeeFeedbacks){
            if(ef.getPatient().getId() == id)
                return ef;
        }

        return null;
    }

}
