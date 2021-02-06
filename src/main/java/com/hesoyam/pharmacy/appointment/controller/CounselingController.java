package com.hesoyam.pharmacy.appointment.controller;


import com.hesoyam.pharmacy.appointment.DTO.*;
import com.hesoyam.pharmacy.appointment.events.OnCounselingReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingCancellationPeriodExpiredException;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
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
import com.hesoyam.pharmacy.pharmacy.dto.PharmacySearchDTO;

import javax.persistence.PreUpdate;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/counseling", produces = MediaType.APPLICATION_JSON_VALUE)
public class CounselingController {

    @Autowired
    private ICounselingService counselingService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @PostMapping(value = "/free-pharmacies/patient")
    public ResponseEntity<List<PharmacySearchDTO>> getAllPharmaciesWithFreeCounseling(@RequestBody CounselingDateAndTimeDTO counselingDateAndTimeDTO){
        List<Counseling> counselings = counselingService.getAllFreeCounselings();
        List<Counseling> possibleCounselings = new ArrayList<>();

        for(Counseling c: counselings){
            if(c.getDateTimeRange().getFrom().minusMinutes(1).isBefore(counselingDateAndTimeDTO.getDateAndTime()) && c.getDateTimeRange().getTo().plusMinutes(1).isAfter(counselingDateAndTimeDTO.getDateAndTime()))
                possibleCounselings.add(c);
        }

        if(!possibleCounselings.isEmpty()){
            List<PharmacySearchDTO> counselingReservationDTOList = new ArrayList<>();
            for(Counseling c: possibleCounselings){
                if(!checkIfInList(c.getPharmacy().getId(), counselingReservationDTOList)){
                    counselingReservationDTOList.add(new PharmacySearchDTO(c));
                }
            }

            return ResponseEntity.ok().body(counselingReservationDTOList);
        }

        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @PostMapping(value = "/free-pharmacists/patient")
    public ResponseEntity<List<CounselingInfoDTO>> getAvailablePharmacistsByTimeAndPharmacy(@RequestBody CounselingDateAndPharmacyDTO counselingDateAndPharmacyDTO){
        List<Counseling> counselings = counselingService.getFreeCounselingsByPharmacyId(counselingDateAndPharmacyDTO.getId());
        List<Counseling> possibleCounselings = new ArrayList<>();

        for(Counseling c: counselings){
            if(c.getDateTimeRange().getFrom().minusMinutes(1).isBefore(counselingDateAndPharmacyDTO.getDateAndTime()) && c.getDateTimeRange().getTo().plusMinutes(1).isAfter(counselingDateAndPharmacyDTO.getDateAndTime()))
                possibleCounselings.add(c);
        }


        List<CounselingInfoDTO> counselingInfoDTOList = new ArrayList<>();

        possibleCounselings.forEach(counseling -> counselingInfoDTOList.add(new CounselingInfoDTO(counseling)));

        return ResponseEntity.ok().body(counselingInfoDTOList);
    }

    @PostMapping(value = "/reserve")
    public ResponseEntity<CounselingIDDTO> reserveCounseling(@AuthenticationPrincipal User user, @RequestBody CounselingIDDTO counselingIDDTO){

        try{
            Counseling counseling = counselingService.findById(counselingIDDTO.getId());
            Patient patient = patientService.getById(user.getId());

            counseling.setAppointmentStatus(AppointmentStatus.TAKEN);
            counseling.setPatient(patient);

            counseling.update(counseling);

            counseling = counselingService.update(counseling);

            applicationEventPublisher.publishEvent(new OnCounselingReservationCompletedEvent(user));

            return ResponseEntity.ok().body(counselingIDDTO);
        } catch (CounselingNotFoundException | PatientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CounselingIDDTO());
        }

    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/cancel/patient")
    public ResponseEntity<FutureCounselingDTO> cancelFutureCounseling(@RequestBody FutureCounselingDTO futureCounselingDTO){

        try{
            Counseling counseling = counselingService.findById(futureCounselingDTO.getId());

            if(counseling.getDateTimeRange().getFrom().isBefore(LocalDateTime.now().plusDays(1)))
                throw new CounselingCancellationPeriodExpiredException(counseling.getId());

            counseling.setPatient(null);
            counseling.setAppointmentStatus(AppointmentStatus.FREE);

            counseling = counselingService.update(counseling);

            return ResponseEntity.ok().body(new FutureCounselingDTO(counseling));
        } catch (CounselingNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new FutureCounselingDTO());
        } catch (CounselingCancellationPeriodExpiredException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new FutureCounselingDTO());
        }
    }


    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/future/patient")
    public ResponseEntity<List<FutureCounselingDTO>> getFutureCounselingsByPatient(@AuthenticationPrincipal User user){

        List<Counseling> counselings = counselingService.getUpcomingCounselingsByPatient(user.getId());
        List<FutureCounselingDTO> futureCounselingDTO = new ArrayList<>();
        counselings.forEach(counseling -> futureCounselingDTO.add(new FutureCounselingDTO(counseling)));

        return ResponseEntity.status(HttpStatus.OK).body(futureCounselingDTO);
    }


    private boolean checkIfInList(Long id, List<PharmacySearchDTO> list){
        for(PharmacySearchDTO c: list){
            if(c.getId() == id)
                return true;
        }
        return false;
    }
}
