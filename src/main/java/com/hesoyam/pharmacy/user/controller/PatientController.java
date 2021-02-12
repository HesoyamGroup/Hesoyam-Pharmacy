package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineAllergyDTO;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.dto.AllergiesDTO;
import com.hesoyam.pharmacy.user.exceptions.PatientAllergyNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientAlreadyAllergicException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMedicineService medicineService;

    @GetMapping(value = "{patientId}/subscribed/pharmacy/{pharmacyId}")
    public ResponseEntity<Boolean> isPatientSubscribedToPharmacy(@PathVariable Long patientId, @PathVariable Long pharmacyId){
        Boolean b = patientService.isPatientSubscribedToPharmacy(patientId, pharmacyId);
        return ResponseEntity.ok().body(b);
    }

    @GetMapping(value = "/all-allergies")
    public ResponseEntity<List<AllergiesDTO>> getAllergies(HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            List<Medicine> allergies = patientService.getAllergiesByPatientId(user.getId());

            List<AllergiesDTO> allergiesDTOList = new ArrayList<>();

            if(!allergies.isEmpty())
                for(Medicine m: allergies){
                    allergiesDTOList.add(new AllergiesDTO(m.getId(), m.getName(), m.getManufacturer().getName()));
                }

            return ResponseEntity.ok().body(allergiesDTOList);

        } catch (UserNotFoundException | PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }

    @PostMapping(value = "/add-allergy")
    public ResponseEntity<List<AllergiesDTO>> addAllergy(@RequestBody MedicineAllergyDTO medicineAllergyDTO, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            Patient patient = patientService.getById(user.getId());

            Medicine addedMedicine = medicineService.findById(medicineAllergyDTO.getId());

            if(patient.getAllergies().contains(addedMedicine))
                throw new PatientAlreadyAllergicException(addedMedicine.getId());

            patient.getAllergies().add(addedMedicine);

            patientService.update(patient);

            List<AllergiesDTO> allergiesDTOList = new ArrayList<>();

            for(Medicine m: patient.getAllergies()){
                allergiesDTOList.add(new AllergiesDTO(m.getId(), m.getName(), m.getManufacturer().getName()));
            }

            return ResponseEntity.ok().body(allergiesDTOList);

        } catch (UserNotFoundException | PatientNotFoundException | MedicineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        } catch (PatientAlreadyAllergicException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @PostMapping(value = "/delete-allergy")
    public ResponseEntity<List<AllergiesDTO>> deleteAllergy(@RequestBody MedicineAllergyDTO medicineAllergyDTO, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            Patient patient = patientService.getById(user.getId());

            Medicine deletedAllergy = medicineService.findById(medicineAllergyDTO.getId());

            if(!patient.getAllergies().contains(deletedAllergy))
                throw new PatientAllergyNotFoundException(deletedAllergy.getId());

            patient.getAllergies().remove(deletedAllergy);

            patientService.update(patient);

            List<AllergiesDTO> allergiesDTOList = new ArrayList<>();

            for(Medicine m: patient.getAllergies()){
                allergiesDTOList.add(new AllergiesDTO(m.getId(), m.getName(), m.getManufacturer().getName()));
            }

            return ResponseEntity.ok().body(allergiesDTOList);

        } catch (UserNotFoundException | PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        } catch (MedicineNotFoundException | PatientAllergyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }

    }

    @GetMapping(value = "/not-allergic-to")
    public ResponseEntity<List<AllergiesDTO>> getAllMedicineNotAllergicTo(HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            List<Medicine> notAllergicTo = patientService.getMedicineNotAllergicToByPatientId(user.getId());

            List<AllergiesDTO> notAllergicToAllergiesDTOList = new ArrayList<>();

            if(!notAllergicTo.isEmpty()){
                for(Medicine m: notAllergicTo){
                    notAllergicToAllergiesDTOList.add(new AllergiesDTO(m.getId(), m.getName(), m.getManufacturer().getName()));
                }
            }

            return ResponseEntity.ok().body(notAllergicToAllergiesDTOList);

        } catch (UserNotFoundException | PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }
}
