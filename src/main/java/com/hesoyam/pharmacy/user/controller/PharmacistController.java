package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.DTO.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
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
@RequestMapping(value = "pharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacistController {
    @Autowired
    private IPharmacistService pharmacistService;

    @GetMapping(value = "pharmacy/{id}")
    public ResponseEntity<List<EmployeeBasicDTO>> getWorkingPharmacistsAtPharmacy(@PathVariable Long id){
        List<Pharmacist> pharmacists = pharmacistService.getWorkingPharmacistsAtPharmacy(id);
        List<EmployeeBasicDTO> employees = new ArrayList<>();
        pharmacists.forEach((pharmacist) -> employees.add(new EmployeeBasicDTO(pharmacist)));
        return new ResponseEntity<>(employees, pharmacists.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
