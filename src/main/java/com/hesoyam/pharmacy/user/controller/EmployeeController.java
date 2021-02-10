package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private IPharmacistService pharmacistService;

    @Autowired
    private IDermatologistService dermatologistService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeBasicDTO> getEmployee(@PathVariable Long id){
        try{
            Employee employee = employeeService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(new EmployeeBasicDTO(employee));
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/all-pharmacies-pharmacist")
    @PreAuthorize("hasAnyRole('PHARMACIST')")
    public ResponseEntity<List<PharmacyDTO>> getAllPharmacies(@AuthenticationPrincipal Pharmacist pharmacist){
        List<Pharmacy> pharmacies = new ArrayList<>();
        List<PharmacyDTO> converted = new ArrayList<>();

        try {
            pharmacist = pharmacistService.getById(pharmacist.getId());
            pharmacies.add(pharmacist.getPharmacy());
            pharmacies.forEach(pharmacy -> converted.add(new PharmacyDTO(pharmacyService.findOne(pharmacy.getId()))));
        } catch (PharmacistNotFoundException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(converted, HttpStatus.OK);
    }

    @GetMapping(value = "/all-pharmacies-dermatologist")
    @PreAuthorize("hasAnyRole('DERMATOLOGIST')")
    public ResponseEntity<List<PharmacyDTO>> getAllPharmacies(@AuthenticationPrincipal Dermatologist dermatologist){
        List<Pharmacy> pharmacies = new ArrayList<>();
        List<PharmacyDTO> converted = new ArrayList<>();
        try {
            dermatologist = dermatologistService.getById(dermatologist.getId());
            pharmacies.addAll(dermatologist.getPharmacies());
            pharmacies.forEach(pharmacy -> converted.add(new PharmacyDTO(pharmacyService.findOne(pharmacy.getId()))));
        } catch (DermatologistNotFoundException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(converted, HttpStatus.OK);
    }
}
