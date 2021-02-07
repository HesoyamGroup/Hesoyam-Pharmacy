package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.DTO.CancelledAppointmentDTO;
import com.hesoyam.pharmacy.appointment.DTO.CheckUpDTO;
import com.hesoyam.pharmacy.appointment.DTO.CounselingDTO;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.util.search.UserSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ILoyaltyAccountService loyaltyAccountService;

    @GetMapping(value = "/appointments-for-pharmacist/{dateTimeRange}")
    @Secured("ROLE_PHARMACIST")
    public ResponseEntity<List<CounselingDTO>> getCounselingsForPharmacist(@PathVariable String dateTimeRange,
                                                                        HttpServletRequest request){
        String username = extractUsername(request);

        String[] parts = dateTimeRange.split("!");
        LocalDateTime fromDate = LocalDateTime.parse(parts[0].trim().substring(0, parts[0].trim().length() - 1));
        LocalDateTime toDate = LocalDateTime.parse(parts[1].trim().substring(0, parts[0].trim().length() - 1));

        try{
            User user = userService.findByEmail(username);
            List<CounselingDTO> counselings = convertToCounselingDTO(appointmentService.getCounselingsForPharmacist(
                    new DateTimeRange(fromDate, toDate), (Pharmacist) user));

            return ResponseEntity.ok().body(counselings);
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

}
