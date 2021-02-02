package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.dto.CheckUpDTO;
import com.hesoyam.pharmacy.appointment.dto.CounselingDTO;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            converted.add(new CounselingDTO(counseling.getPatient().getEmail(), counseling.getPatient().getFirstName(),
                    counseling.getPatient().getLastName(), counseling.getDateTimeRange().getFrom(),
                    counseling.getDateTimeRange().getTo()));
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
            converted.add(new CheckUpDTO(checkUp.getPatient().getFirstName(), checkUp.getPatient().getLastName(),
                    checkUp.getPatient().getEmail(), checkUp.getPharmacy().getName(),
                    checkUp.getDateTimeRange().getFrom(), checkUp.getDateTimeRange().getTo()));
        }
        return converted;
    }

    private String extractUsername(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return tokenUtils.getUsernameFromToken(token);
    }

}
