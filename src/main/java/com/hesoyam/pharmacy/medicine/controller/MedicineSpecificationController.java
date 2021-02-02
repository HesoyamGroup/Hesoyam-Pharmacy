package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineSpecificationFindResultDTO;
import com.hesoyam.pharmacy.medicine.service.IMedicineSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/medicine-spec")
public class MedicineSpecificationController {

    @Autowired
    private IMedicineSpecificationService medicineSpecificationService;

    @GetMapping("/id/{id}")
    public ResponseEntity<MedicineSpecificationFindResultDTO> getById(@PathVariable("id")Long id){
        try{
            return ResponseEntity.ok(medicineSpecificationService.getById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
