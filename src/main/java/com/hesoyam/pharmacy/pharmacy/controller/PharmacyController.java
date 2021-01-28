package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {
    @Autowired
    private IPharmacyService pharmacyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PharmacyDTO> getPharmacy(@PathVariable Long id){

        try{
            Pharmacy pharmacy = pharmacyService.findOne(id);
            return new ResponseEntity<>(new PharmacyDTO(pharmacy), HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/medicine-{id}")
    public ResponseEntity<List<PharmacyDTO>> getPharmaciesWithMedicine(@PathVariable Long id){

        try{
            List<Pharmacy> pharmacies = pharmacyService.findAllPharmaciesByMedicine(id);
            List<PharmacyDTO> retVal = new ArrayList<>();

            for(Pharmacy p: pharmacies){
                retVal.add(new PharmacyDTO(p));
            }

            return  new ResponseEntity<>(retVal, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
