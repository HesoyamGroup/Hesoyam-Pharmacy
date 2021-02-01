package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.impl.PharmacyService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/administrator", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdministratorController {
    @Autowired
    private PharmacyService pharmacyService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("pharmacy/id")
    public ResponseEntity<Long> getPharmacyId(@AuthenticationPrincipal User user){
        try {
            Pharmacy pharmacy = pharmacyService.getByAdministrator(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(pharmacy.getId());
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);
        }

    }
}
