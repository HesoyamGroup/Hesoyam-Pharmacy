package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    @Autowired
    private IPatientService patientService;

    @GetMapping(value = "{patientId}/subscribed/pharmacy/{pharmacyId}")
    public ResponseEntity<Boolean> isPatientSubscribedToPharmacy(@PathVariable Long patientId, @PathVariable Long pharmacyId){
        Boolean b = patientService.isPatientSubscribedToPharmacy(patientId, pharmacyId);
        return ResponseEntity.ok().body(b);
    }
}
