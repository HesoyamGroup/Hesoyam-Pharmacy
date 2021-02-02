package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineReservationCancellationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.events.OnMedicineReservationCompletedEvent;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationExpiredCancellationException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotCreatedException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping("/get-reservations")
    public ResponseEntity<List<MedicineReservation>> getAllMedicineReservationsByPatient(HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(email);
            return ResponseEntity.ok().body(medicineReservationService.getByPatientId(user.getId()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<MedicineReservation> create(@RequestBody MedicineReservationDTO medicineReservationDTO, HttpServletRequest request){
        MedicineReservation medicineReservation = new MedicineReservation();
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);
        Patient patient = new Patient();
        try{
             User user = userService.findByEmail(email);
             patient = patientService.getById(user.getId());
        } catch (PatientNotFoundException | UserNotFoundException e) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
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

    @PostMapping("/cancel-reservation")
    public ResponseEntity<Long> cancelMedicineReservation(@RequestBody MedicineReservationCancellationDTO medicineReservationCancellationDTO, HttpServletRequest request){

        /*String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(email);
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Long.parseLong("-1"));
        }*/

        try{
            MedicineReservation medicineReservation = medicineReservationService.getById(medicineReservationCancellationDTO.getId());

            if(medicineReservation.getPickUpDate().isBefore(LocalDateTime.now().plusDays(1)))
                throw new MedicineReservationExpiredCancellationException(medicineReservation.getId());
            if(medicineReservation.getMedicineReservationStatus() != MedicineReservationStatus.CREATED)
                throw new MedicineReservationNotCreatedException(medicineReservation.getId());

            medicineReservation.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
            medicineReservationService.update(medicineReservation);

            return ResponseEntity.ok().body(medicineReservation.getId());
        } catch (MedicineReservationNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Long.parseLong("-2"));
        } catch (MedicineReservationExpiredCancellationException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Long.parseLong("-3"));
        } catch(MedicineReservationNotCreatedException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Long.parseLong("-4"));
        }
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
