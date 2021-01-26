package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private IMedicineService medicineService;


    //todo: Perform authorization
    @GetMapping("/all")
    public ResponseEntity<List<Medicine>> getAllMedicines(){
        return ResponseEntity.ok(medicineService.getAll());
    }

    @GetMapping("/types")
    public ResponseEntity<List<MedicineType>> getAllMedicineTypes(){
        return ResponseEntity.ok(medicineService.getAllMedicineTypes());
    }

    @PostMapping("/create")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Medicine> create(@RequestBody(required = true) @Valid Medicine medicine){
        //Validation is done on model level + db level.
        return ResponseEntity.ok(medicineService.create(medicine));
    }
}
