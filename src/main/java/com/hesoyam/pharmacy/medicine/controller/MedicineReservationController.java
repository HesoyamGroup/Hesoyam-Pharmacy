package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineReservationCancellationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationPickupDTO;
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
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IUserService;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    EmailClient client;

    @GetMapping("/all")
    public ResponseEntity<List<MedicineReservation>> getAllMedicineReservations(){
        return ResponseEntity.ok(medicineReservationService.getAll());
    }

    @GetMapping("/get-reservations")
    public ResponseEntity<List<MedicineReservationDTO>> getAllMedicineReservationsByPatient(@AuthenticationPrincipal User user){

            List<MedicineReservation> medicineReservationList = medicineReservationService.getByPatientId(user.getId());
            List<MedicineReservationDTO> medicineReservationDTOList = new ArrayList<>();
            medicineReservationList.forEach(medicineReservation -> medicineReservationDTOList.add(new MedicineReservationDTO(medicineReservation)));

            return ResponseEntity.ok().body(medicineReservationDTOList);
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

    @GetMapping(value = "/pickup/{code}")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<MedicineReservationPickupDTO> getMedicineReservationForCode(@PathVariable String code,
                                                                                      @AuthenticationPrincipal Pharmacist user){
        long pharmacyId = user.getPharmacy().getId();

            MedicineReservation reservation = medicineReservationService.findByCodeAndPharmacy(code, pharmacyId);
            if(reservation != null)
                return new ResponseEntity<>(new MedicineReservationPickupDTO(reservation), HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.OK);


    }

    @PostMapping(value = "/confirm-pickup")
    @PreAuthorize("hasRole('PHARMACIST')")
    public boolean confirmPickup(@RequestBody @Valid String reservationCode, @AuthenticationPrincipal User user){
        String extractCode = reservationCode.split(":")[1].substring(1, reservationCode.split(":")[1].length() -2);
        String emailBody = "This email is confirmation that you have successfully picked up order #";
        try {
            MedicineReservation toUpdate =  medicineReservationService.getByMedicineReservationCode(extractCode);
            if(!toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.COMPLETED) &&
                    !(toUpdate.getPickUpDate().isBefore(LocalDateTime.now()) && toUpdate.getPickUpDate().isAfter(
                            LocalDateTime.now().minus(24, ChronoUnit.HOURS)))) {

                toUpdate.setMedicineReservationStatus(MedicineReservationStatus.COMPLETED);
                medicineReservationService.update(toUpdate);

                // TODO: Izmeniti email da bude klijentski
                EmailObject email = new EmailObject("Medicine pickup confirmation!",
//                        toUpdate.getPatient().getEmail(), emailBody + toUpdate.getCode() + "!");
                        "krickovicluka@gmail.com", emailBody + toUpdate.getCode() + "!");
                client.sendEmail(email);
                return true;

            }
            return false;
        } catch (MedicineReservationNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    @PostMapping(value = "/cancel-pickup")
    @PreAuthorize("hasRole('PHARMACIST')")
    public boolean cancelPickup(@RequestBody @Valid String reservationCode){
        MedicineReservation toUpdate = null;
        String extractCode = reservationCode.split(":")[1].substring(1, reservationCode.split(":")[1].length() -2);
        try {
            toUpdate = medicineReservationService.getByMedicineReservationCode(extractCode);
            if(toUpdate != null) {
                if (toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.COMPLETED)) {
                    toUpdate.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
                    medicineReservationService.update(toUpdate);
                    return true;
                }
            }
            return false;
        } catch (MedicineReservationNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

}
