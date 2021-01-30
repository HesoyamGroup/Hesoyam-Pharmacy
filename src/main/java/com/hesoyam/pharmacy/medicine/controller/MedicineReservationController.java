package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.DTO.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.events.OnMedicineReservationCompletedEvent;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationItemService;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IUserService;
import com.sun.mail.iap.Response;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/medicine-reservation")
public class MedicineReservationController {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IMedicineReservationItemService medicineReservationItemService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/all")
    public ResponseEntity<List<MedicineReservation>> getAllMedicineReservations(){
        return ResponseEntity.ok(medicineReservationService.getAll());
    }

    @PostMapping("/create")
    //TODO: dodati @secured?
    public ResponseEntity<MedicineReservation> create(@RequestBody MedicineReservationDTO medicineReservationDTO, HttpServletRequest request){
        MedicineReservation medicineReservation = new MedicineReservation();
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);
        Patient patient = new Patient();
        try{
             User user = userService.findByEmail(email);
             patient = patientService.getById(user.getId());
        } catch (PatientNotFoundException | UserNotFoundException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        medicineReservation.setPatient(patient);
        medicineReservation.setMedicineReservationItems(medicineReservationDTO.getMedicineReservationItemList());
        medicineReservation.setPickUpDate(medicineReservationDTO.getPickUpDate());
        medicineReservation.setCode(generateString());
        medicineReservation.setMedicineReservationStatus(MedicineReservationStatus.CREATED);

        try{
            User user = userService.findByEmail(email);
            applicationEventPublisher.publishEvent(new OnMedicineReservationCompletedEvent(user, medicineReservation));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(medicineReservationService.create(medicineReservation));
    }

    private String generateString() {
        String SOURCES ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        int length = 50;
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
}
