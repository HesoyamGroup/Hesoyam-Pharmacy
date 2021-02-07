package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineBasicInfoDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineSearchDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineSearchResultDTO;
import com.hesoyam.pharmacy.medicine.events.MedicineRunningLowEvent;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.user.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private IMedicineService medicineService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    IAdministratorService administratorService;

    @GetMapping("")
    public ResponseEntity<List<MedicineBasicInfoDTO>> getMedicines(){
        List<Medicine> medicines = medicineService.getAll();
        List<MedicineBasicInfoDTO> medicinesDTO = new ArrayList<>();
        medicines.forEach(medicine -> medicinesDTO.add(new MedicineBasicInfoDTO(medicine)));
        return ResponseEntity.ok(medicinesDTO);
    }


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

    @GetMapping("/search")
    public ResponseEntity<List<MedicineSearchResultDTO>> search(@RequestParam(required = false) String name, @RequestParam(required = false) MedicineType medicineType, @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating, @RequestParam(required = false) Integer page){
        if(page == null || page < 1) page = 1;
        MedicineSearchDTO medicineSearchDTO = new MedicineSearchDTO(name, medicineType, minRating, maxRating, page);
        return ResponseEntity.ok(medicineService.search(medicineSearchDTO));
    }

    @GetMapping("/check-availability/{medicineNameAndQuantity}")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable String medicineNameAndQuantity){
        String[] parts = medicineNameAndQuantity.split("&&");
        boolean isAvailable = medicineService.checkAvailability(parts[0], Integer.parseInt(parts[1]), Long.parseLong(parts[2]));
        if(!isAvailable){
            applicationEventPublisher.publishEvent(new MedicineRunningLowEvent(parts[0],
                    administratorService.getAdministratorsForPharmacyId(Long.parseLong(parts[2]))));
        }
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
}
