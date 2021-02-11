package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.dto.*;
import com.hesoyam.pharmacy.appointment.events.OnCheckupReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupCancellationPeriodExpiredException;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.appointment.service.ITherapyItemService;
import com.hesoyam.pharmacy.appointment.service.ITherapyService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;
import com.hesoyam.pharmacy.prescription.exceptions.PatientIsAllergicException;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.service.IPrescriptionService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserPenalizedException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/checkup", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckUpController {

    @Autowired
    private ICheckUpService checkUpService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IPrescriptionService prescriptionService;

    @Autowired
    private ITherapyService therapyService;

    @Autowired
    private ITherapyItemService therapyItemService;

    @Autowired
    private IMedicineService medicineService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "/free/dermatologist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFreeCheckUp(@AuthenticationPrincipal User user, @RequestBody FreeCheckupDTO freeCheckupDTO, @PathVariable Long id){

        try{
            CheckUp checkUp = checkUpService.createFreeCheckUp(user, freeCheckupDTO, id);
            if(checkUp == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Given date range is not available");
            else
                return ResponseEntity.status(HttpStatus.CREATED).body(new FreeCheckupDTO(checkUp));
        } catch (DermatologistNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Administrator is not employee's boss");
        }
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/free/dermatologist/{dermatologistId}")
    public ResponseEntity<List<FreeCheckupDTO>> getUpcomingFreeCheckupsByEmployee(@PathVariable Long dermatologistId, @RequestParam String pharmacy) {

        List<CheckUp> checkUps = checkUpService.getUpcomingFreeCheckupsByEmployeeAndPharmacy(dermatologistId, pharmacy);
        List<FreeCheckupDTO> freeCheckUps = new ArrayList<>();
        checkUps.forEach(checkUp -> freeCheckUps.add(new FreeCheckupDTO(checkUp)));

        return ResponseEntity.status(HttpStatus.OK).body(freeCheckUps);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/reserve")
    public ResponseEntity<FreeCheckupDTO> reserveFreeCheckup(@AuthenticationPrincipal User user, @RequestBody FreeCheckupDTO freeCheckupDTO){
        try{
            CheckUp checkup = checkUpService.findById(freeCheckupDTO.getId());
            Patient patient = patientService.getById(user.getId());

            if(patient.getPenaltyPoints() >= 3){
                throw new UserPenalizedException(patient.getId());
            }

            checkup.setAppointmentStatus(AppointmentStatus.TAKEN);
            checkup.setPatient(patient);

            checkup.update(checkup);

            checkup = checkUpService.update(checkup);

            applicationEventPublisher.publishEvent(new OnCheckupReservationCompletedEvent(user));

            return ResponseEntity.ok().body(freeCheckupDTO);

        } catch (CheckupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FreeCheckupDTO());
        } catch (PatientNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FreeCheckupDTO());
        } catch (UserPenalizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FreeCheckupDTO());
        }
    }


    @GetMapping(value = "/free/pharmacy/{id}")
    public ResponseEntity<List<FreeCheckupDTO>> getFreeCheckupsByPharmacy(@PathVariable Long id){

        List<CheckUp> checkUps = checkUpService.getUpcomingFreeCheckupsByPharmacy(id);
        List<FreeCheckupDTO> freeCheckUps = new ArrayList<>();
        checkUps.forEach(checkUp -> freeCheckUps.add(new FreeCheckupDTO(checkUp)));

        return ResponseEntity.status(HttpStatus.OK).body(freeCheckUps);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/future/patient")
    public ResponseEntity<List<FutureCheckupDTO>> getFutureCheckupsByPatient(@AuthenticationPrincipal User user){

        List<CheckUp> checkUps = checkUpService.getUpcomingCheckupsByPatient(user.getId());
        List<FutureCheckupDTO> futureCheckupDTO = new ArrayList<>();
        checkUps.forEach(checkUp -> futureCheckupDTO.add(new FutureCheckupDTO(checkUp)));

        return ResponseEntity.status(HttpStatus.OK).body(futureCheckupDTO);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/cancel/patient")
    public ResponseEntity<FutureCheckupDTO> cancelFutureCheckup(@RequestBody FutureCheckupDTO futureCheckupDTO){

        try{
            CheckUp checkUp = checkUpService.findById(futureCheckupDTO.getId());

            if(checkUp.getDateTimeRange().getFrom().isBefore(LocalDateTime.now().plusDays(1)))
                throw new CheckupCancellationPeriodExpiredException(checkUp.getId());

            checkUp.setPatient(null);
            checkUp.setAppointmentStatus(AppointmentStatus.FREE);

            checkUp = checkUpService.update(checkUp);

            return ResponseEntity.status(HttpStatus.OK).body(new FutureCheckupDTO(checkUp));

        } catch (CheckupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FutureCheckupDTO());
        } catch (CheckupCancellationPeriodExpiredException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FutureCheckupDTO());
        }

    }

    @GetMapping(value = "get-all-checkups-for-patient/{patientEmail}")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<List<CheckUpDTO>> getAllCounselings(
            @PathVariable String patientEmail, @AuthenticationPrincipal Dermatologist user
    ){
        Patient patient = patientService.getByEmail(patientEmail);
        if(patient != null) {
            List<CheckUpDTO> dtos = new ArrayList<>();
            List<CheckUp> allCheckUps = checkUpService.getAllCheckUpsForPatientAndDermatologist(patient, user);
            allCheckUps.forEach(checkUp -> dtos.add(new CheckUpDTO(checkUp)));
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/finish-checkup")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> finishCheckup(@RequestBody @Valid CheckupReportDTO checkupReportDTO,
                                                   @AuthenticationPrincipal Dermatologist user){
        Patient patient = patientService.getByEmail(checkupReportDTO.getPatientEmail());
        try {
            CheckUp checkUp = checkUpService.updateCheckupAfterAppointment(patient, checkupReportDTO.getFrom(),
                    checkupReportDTO.getReport(), user);
            List<PrescriptionItem> converted = convertPrescriptionItems(checkupReportDTO.getPrescriptionItems());
            try {
                prescriptionService.createPrescription(converted, patient, checkUp.getPharmacy().getId());
                List<TherapyItem> therapyItems = therapyItemService.createFromPrescriptionItems(
                        checkupReportDTO.getPrescriptionItems());
                therapyService.createFromItems(therapyItems);
                therapyItemService.createFromList(therapyItems);
            } catch (PatientIsAllergicException e1) {
                e1.printStackTrace();
                return new ResponseEntity<>("Patient is allergic to medicine!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Successfully finished checkup!", HttpStatus.OK);
        } catch (CheckupNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to finish checkup!", HttpStatus.NO_CONTENT);
        }

    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/past/patient")
    public ResponseEntity<List<FutureCheckupDTO>> getPastCounselingsByPatient(@AuthenticationPrincipal User user){

        List<CheckUp> checkUps = checkUpService.getAllCompletedCheckupsByPatient(user.getId());
        List<FutureCheckupDTO> pastCheckupDTO = new ArrayList<>();
        checkUps.forEach(checkup -> pastCheckupDTO.add(new FutureCheckupDTO(checkup)));

        return ResponseEntity.status(HttpStatus.OK).body(pastCheckupDTO);
    }

    private List<PrescriptionItem> convertPrescriptionItems(List<PrescriptionItemDTO> prescriptionItems) {
        List<PrescriptionItem> converted = new ArrayList<>();
        for(PrescriptionItemDTO prescriptionItem : prescriptionItems){
            PrescriptionItem item = new PrescriptionItem();
            item.setMedicine(medicineService.findByMedicineName(prescriptionItem.getMedicine()).get(0));
            item.setQuantity(prescriptionItem.getQuantity());
            converted.add(item);
        }
        return converted;
    }
}
