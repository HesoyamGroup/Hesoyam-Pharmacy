package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.feedback.dto.DermatologistFeedbackDTO;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {

    @Autowired
    private ICheckUpService checkUpService;

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

}
