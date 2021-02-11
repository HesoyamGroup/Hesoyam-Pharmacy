package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineReservationCancellationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationPickupDTO;
import com.hesoyam.pharmacy.medicine.events.OnMedicineReservationCompletedEvent;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationExpiredCancellationException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotCreatedException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationItemService;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
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
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value="/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IInventoryItemService inventoryItemService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IMedicineService medicineService;

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
            medicineReservationList.forEach(medicineReservation -> {
                medicineReservationDTOList.add(new MedicineReservationDTO(medicineReservation));
            });

            return ResponseEntity.ok().body(medicineReservationDTOList);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody MedicineReservationDTO medicineReservationDTO, @AuthenticationPrincipal User user){

        try {

            medicineReservationService.createMedicineReservation(medicineReservationDTO, user);
            return ResponseEntity.ok().body(null);
        } catch (PatientNotFoundException | MedicineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }



    @PostMapping("/cancel-reservation")
    public ResponseEntity cancelMedicineReservation(@RequestBody MedicineReservationCancellationDTO medicineReservationCancellationDTO, @AuthenticationPrincipal User user){

            try {
                MedicineReservation medicineReservation = medicineReservationService.getByMedicineReservationCode(medicineReservationCancellationDTO.getReservationCode());
                if(medicineReservation.getPickUpDate().isBefore(LocalDateTime.now().plusDays(1)))
                {
                    throw new MedicineReservationExpiredCancellationException(medicineReservation.getId());
                }

                medicineReservation.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
                medicineReservationService.update(medicineReservation);

                InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(medicineReservationCancellationDTO.getPharmacyId(), medicineReservationCancellationDTO.getMedicineId());

                inventoryItem.setAvailable(inventoryItem.getAvailable()+1);
                inventoryItem.setReserved(inventoryItem.getReserved()-1);

                inventoryItemService.update(inventoryItem);

                return ResponseEntity.ok().body(null);
            } catch (MedicineReservationNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } catch (MedicineReservationExpiredCancellationException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

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
            if (medicineReservationService.cancelPickup(toUpdate)) return true;
            return false;
        } catch (MedicineReservationNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }



}
