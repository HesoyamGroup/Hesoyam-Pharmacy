package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.DTO.PharmacyCreateDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {
    @Autowired
    private IPharmacyService pharmacyService;

    @PostMapping("/create")
    public ResponseEntity<Pharmacy> create(@RequestBody @Valid PharmacyCreateDTO pharmacyCreateDTO, BindingResult errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Pharmacy savedPharmacy = pharmacyService.create(pharmacyCreateDTO);
        return ResponseEntity.ok(savedPharmacy);
    }
}
