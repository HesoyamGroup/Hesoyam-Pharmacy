package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.DTO.FutureCheckupDTO;
import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.events.OnCheckupReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupCancellationPeriodExpiredException;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/checkup", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckUpController {

    @Autowired
    private ICheckUpService checkUpService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

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
        }
    }

    @GetMapping(value = "/free/pharmacy/{id}")
    ResponseEntity<List<FreeCheckupDTO>> getFreeCheckupsByPharmacy(@PathVariable Long id){

        List<CheckUp> checkUps = checkUpService.getUpcomingFreeCheckupsByPharmacy(id);
        List<FreeCheckupDTO> freeCheckUps = new ArrayList<>();
        checkUps.forEach(checkUp -> freeCheckUps.add(new FreeCheckupDTO(checkUp)));

        return ResponseEntity.status(HttpStatus.OK).body(freeCheckUps);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/future/patient")
    ResponseEntity<List<FutureCheckupDTO>> getFutureCheckupsByPatient(@AuthenticationPrincipal User user){

        List<CheckUp> checkUps = checkUpService.getUpcomingCheckupsByPatient(user.getId());
        List<FutureCheckupDTO> futureCheckupDTO = new ArrayList<>();
        checkUps.forEach(checkUp -> futureCheckupDTO.add(new FutureCheckupDTO(checkUp)));

        return ResponseEntity.status(HttpStatus.OK).body(futureCheckupDTO);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/cancel/patient")
    ResponseEntity<FutureCheckupDTO> cancelFutureCheckup(@RequestBody FutureCheckupDTO futureCheckupDTO){

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
}
