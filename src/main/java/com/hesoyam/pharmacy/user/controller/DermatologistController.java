package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.DTO.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistController {
    @Autowired
    private IDermatologistService dermatologistService;

    @GetMapping(value = "pharmacy/{id}")
    public ResponseEntity<List<EmployeeBasicDTO>> getWorkingDermatologistsAtPharmacy(@PathVariable Long id){
        List<Dermatologist> dermatologists = dermatologistService.getWorkingDermatologistsAtPharmacy(id);
        List<EmployeeBasicDTO> employees = new ArrayList<>();
        dermatologists.forEach( dermatologist -> employees.add(new EmployeeBasicDTO(dermatologist)));
        return new ResponseEntity<>(employees, dermatologists.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
