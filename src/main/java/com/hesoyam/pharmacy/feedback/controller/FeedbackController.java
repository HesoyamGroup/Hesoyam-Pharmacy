package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.feedback.dto.DermatologistFeedbackDTO;
import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import com.hesoyam.pharmacy.feedback.model.Feedback;
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
    IEmployeeFeedbackService employeeFeedbackService;

    @Autowired
    IFeedbackService feedbackService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPatientService patientService;

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/checkups")
    public ResponseEntity<List<DermatologistFeedbackDTO>> getPastCheckups(@AuthenticationPrincipal User user){

        List<CheckUp> pastCheckups = checkUpService.getAllCompletedCheckupsByPatient(user.getId());

        List<DermatologistFeedbackDTO> dermatologistFeedbackDTOList = new ArrayList<>();
        if(!pastCheckups.isEmpty()){
            for(CheckUp c: pastCheckups){
                if(!isDermatologistInList(c.getDermatologist().getId(), dermatologistFeedbackDTOList)){
                    dermatologistFeedbackDTOList.add(new DermatologistFeedbackDTO(c));
                }
            }
        }

        return ResponseEntity.ok().body(dermatologistFeedbackDTOList);
    }

    private boolean isDermatologistInList(Long id, List<DermatologistFeedbackDTO> dermatologistFeedbackDTOList){
        for(DermatologistFeedbackDTO d: dermatologistFeedbackDTOList){
            if(d.getDermatologistId() == id)
                return true;
        }
        return false;
    }

    //@Secured("ROLE_PATIENT")
    @PostMapping(value = "/dermatologist")
    public ResponseEntity<Double> dermatologistFeedback(@RequestBody DermatologistFeedbackDTO dermatologistFeedbackDTO, @AuthenticationPrincipal User user) {

        List<EmployeeFeedback> employeeFeedbacks = employeeFeedbackService.findByEmployeeId(dermatologistFeedbackDTO.getDermatologistId());

        EmployeeFeedback employeeFeedback = findByPatientId(user.getId(), employeeFeedbacks);
        if(employeeFeedback != null){
            employeeFeedback.setRating(dermatologistFeedbackDTO.getYourRating());
            employeeFeedback.setComment(dermatologistFeedbackDTO.getYourComment());

            employeeFeedbackService.update(employeeFeedback);
        }
        else{
            try {
                employeeFeedback = new EmployeeFeedback();
                employeeFeedback.setComment(dermatologistFeedbackDTO.getYourComment());
                employeeFeedback.setRating(dermatologistFeedbackDTO.getYourRating());
                employeeFeedback.setEmployee(employeeService.getOne(dermatologistFeedbackDTO.getDermatologistId()));
                employeeFeedback.setPatient(patientService.getById(user.getId()));

                employeeFeedback = employeeFeedbackService.create(employeeFeedback);
            }catch (PatientNotFoundException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-2.0);
            }
        }
        double newRating = employeeFeedbackService.calculateEmployeeRating(dermatologistFeedbackDTO.getDermatologistId());
        try{
            employeeService.updateRating(dermatologistFeedbackDTO.getDermatologistId(), newRating);
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
