package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.dto.AppointmentBookingDTO;
import com.hesoyam.pharmacy.appointment.dto.CancelledAppointmentDTO;
import com.hesoyam.pharmacy.appointment.dto.CheckUpDTO;
import com.hesoyam.pharmacy.appointment.dto.CounselingDTO;
import com.hesoyam.pharmacy.appointment.events.OnCheckupReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.events.OnCounselingReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.util.search.UserSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ICounselingService counselingService;

    @Autowired
    private ICheckUpService checkUpService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPharmacistService pharmacistService;

    @Autowired
    private IDermatologistService dermatologistService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(value = "/appointments-for-pharmacist/{dateTimeRange}")
    @Secured("ROLE_PHARMACIST")
    public ResponseEntity<List<CounselingDTO>> getCounselingsForPharmacist(@PathVariable String dateTimeRange,
                                                                        HttpServletRequest request){
        String username = extractUsername(request);

        String[] parts = dateTimeRange.split("!");
        LocalDateTime fromDate = LocalDateTime.parse(parts[0].trim().substring(0, parts[0].trim().length() - 1));
        LocalDateTime toDate = LocalDateTime.parse(parts[1].trim().substring(0, parts[0].trim().length() - 1));
        if(fromDate.isBefore(toDate)) {

            try {
                User user = userService.findByEmail(username);
                List<CounselingDTO> counselings = convertToCounselingDTO(appointmentService.getCounselingsForPharmacist(
                        new DateTimeRange(fromDate, toDate), (Pharmacist) user));

                return ResponseEntity.ok().body(counselings);
            } catch (UserNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();

        }
    }

    private List<CounselingDTO> convertToCounselingDTO(List<Counseling> counselingsForPharmacist) {
        List<CounselingDTO> converted = new ArrayList<>();
        for(Counseling counseling : counselingsForPharmacist){
            if(counseling.getPatient() != null) {
                converted.add(new CounselingDTO(counseling));
            }
        }
        return converted;
    }

    @GetMapping(value = "/appointments-for-dermatologist/{dateTimeRange}")
    @Secured("ROLE_DERMATOLOGIST")
    public ResponseEntity<List<CheckUpDTO>> getAppontmentsForDermatologist(@PathVariable String dateTimeRange,
                                                                           HttpServletRequest request){
        String username = extractUsername(request);

        String[] parts = dateTimeRange.split("!");
        LocalDateTime fromDate = LocalDateTime.parse(parts[0].trim().substring(0, parts[0].trim().length() - 1));
        LocalDateTime toDate = LocalDateTime.parse(parts[1].trim().substring(0, parts[0].trim().length() - 1));

        try{
            User user = userService.findByEmail(username);
            List<CheckUpDTO> checkUps = convertToCheckUpDTO(appointmentService.getCheckUpsForDermatologist(
                    new DateTimeRange(fromDate, toDate), (Dermatologist) user));

            return ResponseEntity.ok().body(checkUps);
        }
        catch (UserNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

    private List<CheckUpDTO> convertToCheckUpDTO(List<CheckUp> checkUpsForDermatologist) {
        List<CheckUpDTO> converted = new ArrayList<>();
        for(CheckUp checkUp : checkUpsForDermatologist){
            if(checkUp.getPatient() != null) {
                converted.add(new CheckUpDTO(checkUp));
            }
        }
        return converted;
    }

    private String extractUsername(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping(value = "/search-for-user/{query}")
    @PreAuthorize("hasAnyRole('PHARMACIST', 'DERMATOLOGIST')")
    public ResponseEntity<List<UserSearchResult>> searchPatients(@AuthenticationPrincipal User user,
                                                                 @PathVariable String query){
        return new ResponseEntity<>(appointmentService.searchUsers((Employee) user, query), HttpStatus.OK);
    }

    @PostMapping(value = "/patient-didnt-show")
    @PreAuthorize("hasAnyRole('PHARMACIST', 'DERMATOLOGIST')")
    public ResponseEntity<String> patientDidntShow(@AuthenticationPrincipal User user, @RequestBody @Valid
                                                   CancelledAppointmentDTO cancelledAppointmentDTO){
        Patient patient = patientService.getByEmail(cancelledAppointmentDTO.getPatientEmail());

        if(user.getRoleEnum().equals(RoleEnum.PHARMACIST)){
            counselingService.cancelCounseling(patient, cancelledAppointmentDTO.getFrom(),
                    (Pharmacist) user);
        } else {
            checkUpService.cancelCheckup(patient, cancelledAppointmentDTO.getFrom(), (Dermatologist) user);
        }

        patientService.penalizeForMissingAppointment(patient);

        return new ResponseEntity<>("Successfully cancelled appointment!", HttpStatus.OK);
    }

    @GetMapping(value = "/check-new-appointment/{emailTime}")
    @PreAuthorize("hasAnyRole('PHARMACIST', 'DERMATOLOGIST')")
    public ResponseEntity<Boolean> checkNewAppointment(@AuthenticationPrincipal User user, @PathVariable String emailTime){
        String[] parts = emailTime.split("&&");
        LocalDateTime from = LocalDateTime.parse(parts[1]);
        LocalDateTime to = LocalDateTime.parse(parts[2]);
        if(isRangeValid(from, to)) {
            Patient patient = patientService.getByEmail(parts[0]);
            boolean isFree = appointmentService.checkNewAppointment(user, patient, new DateTimeRange(from, to));
            return new ResponseEntity<>(isFree, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping(value = "/book-new-appointment")
    @PreAuthorize("hasAnyRole('PHARMACIST', 'DERMATOLOGIST')")
    public ResponseEntity<String> bookNewAppointment(@RequestBody @Valid AppointmentBookingDTO appointmentBookingDTO,
                                                     @AuthenticationPrincipal User user){
        if(isRangeValid(appointmentBookingDTO.getFrom(), appointmentBookingDTO.getTo())) {

                Appointment appointment = null;
                Patient patient = patientService.getByEmail(appointmentBookingDTO.getPatientEmail());

                if(user.getRoleEnum().equals(RoleEnum.PHARMACIST)){
                    Pharmacist pharmacist = null;
                    try {
                        pharmacist = pharmacistService.getById(user.getId());
                        if(pharmacist != null) {
                            appointment = appointmentService.createNewAppointment(patient, pharmacist,
                                    appointmentBookingDTO.getPharmacyId(), new DateTimeRange(appointmentBookingDTO.getFrom(),
                                            appointmentBookingDTO.getTo()), appointmentBookingDTO.getPrice());
                        }
                    } catch (PharmacistNotFoundException e) {
                        return ResponseEntity.badRequest().build();
                    }
                }
                else {
                    Dermatologist dermatologist = null;
                    try {
                        dermatologist = dermatologistService.getById(user.getId());
                        if(dermatologist != null){
                            appointment = appointmentService.createNewAppointment(patient, dermatologist,
                                    appointmentBookingDTO.getPharmacyId(), new DateTimeRange(appointmentBookingDTO.getFrom(),
                                            appointmentBookingDTO.getTo()), appointmentBookingDTO.getPrice());
                        }
                    } catch (DermatologistNotFoundException e) {
                        return ResponseEntity.badRequest().build();
                    }
                }

            if (appointment != null) {
                sendConfirmationEmail(user, patient);
                return new ResponseEntity<>("Successfully created appointment!", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Failed to create appointment!", HttpStatus.OK);
    }

    private void sendConfirmationEmail(User user, Patient patient) {
        if(user.getRoleEnum().equals(RoleEnum.PHARMACIST))
            applicationEventPublisher.publishEvent(new OnCounselingReservationCompletedEvent(patient));
        else
            applicationEventPublisher.publishEvent(new OnCheckupReservationCompletedEvent(patient));
    }

    private boolean isRangeValid(LocalDateTime from, LocalDateTime to) {
        return from.isBefore(to);
    }
}
