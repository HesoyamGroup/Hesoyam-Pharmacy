package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.dto.MedicineReservationCancellationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationPickupDTO;
import com.hesoyam.pharmacy.medicine.dto.ReservationCodeDTO;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationExpiredCancellationException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserPenalizedException;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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



    @GetMapping("/all")
    public ResponseEntity<List<MedicineReservation>> getAllMedicineReservations(){
        return ResponseEntity.ok(medicineReservationService.getAll());
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/get-reservations")
    public ResponseEntity<List<MedicineReservationDTO>> getAllMedicineReservationsByPatient(@AuthenticationPrincipal User user){

            List<MedicineReservation> medicineReservationList = medicineReservationService.getByPatientId(user.getId());
            List<MedicineReservationDTO> medicineReservationDTOList = new ArrayList<>();
            medicineReservationList.forEach(medicineReservation ->
                medicineReservationDTOList.add(new MedicineReservationDTO(medicineReservation))
            );

            return ResponseEntity.ok().body(medicineReservationDTOList);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/create")
    public ResponseEntity<MedicineReservation> create(@RequestBody MedicineReservationDTO medicineReservationDTO, @AuthenticationPrincipal User user){

        try {
            medicineReservationService.createMedicineReservation(medicineReservationDTO, user);
            return ResponseEntity.ok().build();
        } catch (PatientNotFoundException | MedicineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (UserPenalizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/cancel-reservation")
    public ResponseEntity<MedicineReservation> cancelMedicineReservation(@RequestBody MedicineReservationCancellationDTO medicineReservationCancellationDTO, @AuthenticationPrincipal User user){

            try {
                medicineReservationService.cancelMedicineReservation(medicineReservationCancellationDTO, user);
                return ResponseEntity.ok().build();
            } catch (MedicineReservationNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } catch (MedicineReservationExpiredCancellationException | ObjectOptimisticLockingFailureException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
                return ResponseEntity.ok().build();


    }

    @PostMapping(value = "/confirm-pickup")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Boolean> confirmPickup(@RequestBody @Valid ReservationCodeDTO codeDTO, @AuthenticationPrincipal User user){
        String reservationCode = codeDTO.getReservationCode();
        try {
            boolean success = medicineReservationService.confirmPickup(reservationCode);
            if(success)
                return new ResponseEntity<>(success, HttpStatus.OK);
            else
                return ResponseEntity.notFound().build();
        } catch (MedicineReservationNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ObjectOptimisticLockingFailureException e){
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping(value = "/cancel-pickup")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Boolean> cancelPickup(@RequestBody @Valid ReservationCodeDTO codeDTO){
        MedicineReservation toUpdate = null;
        String extractCode = codeDTO.getReservationCode();
        try {

            toUpdate = medicineReservationService.getByMedicineReservationCode(extractCode);
            if (medicineReservationService.cancelPickup(toUpdate)) return new ResponseEntity<>(true, HttpStatus.OK);
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (MedicineReservationNotFoundException | ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
