package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyCreateDTO;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyDTO;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacySearchDTO;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidPharmacyCreateRequest;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {
    @Autowired
    private IPharmacyService pharmacyService;

    @PostMapping("/create")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Pharmacy> create(@RequestBody @Valid PharmacyCreateDTO pharmacyCreateDTO, BindingResult errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Pharmacy savedPharmacy = null;
        try {
            savedPharmacy = pharmacyService.create(pharmacyCreateDTO);
        } catch (InvalidPharmacyCreateRequest invalidPharmacyCreateRequest) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(savedPharmacy);
    }
    
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

    @GetMapping(value="/all")
    public ResponseEntity<List<PharmacySearchDTO>> getAllPharmacies(){
        List<Pharmacy> pharmacies = pharmacyService.getAll();

        List<PharmacySearchDTO> pharmacySearchDTOList = new ArrayList<>();

        for(Pharmacy p: pharmacies){
            pharmacySearchDTOList.add(new PharmacySearchDTO(p.getId(), p.getName(), p.getRating(), p.getDescription(), p.getAddress().getCity().getCityName(), p.getAddress().getAddressLine(), p.getAddress().getLatitude(), p.getAddress().getLongitude()));
        }

        return ResponseEntity.ok().body(pharmacySearchDTOList);
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
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
