package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.dto.PharmacistDTO;
import com.hesoyam.pharmacy.user.dto.PharmacistDetailsDTO;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import com.hesoyam.pharmacy.user.service.IUserService;
import com.hesoyam.pharmacy.util.search.UserSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "pharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacistController {
    @Autowired
    private IPharmacistService pharmacistService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMINISTRATOR')")
    public ResponseEntity<List<PharmacistDetailsDTO>> getAllPharmacists(@AuthenticationPrincipal User user){

        List<Pharmacist> pharmacists = pharmacistService.getAll(user);
        List<PharmacistDetailsDTO> pharmacistsDTO = new ArrayList<>();
        pharmacists.forEach(pharmacist -> pharmacistsDTO.add(new PharmacistDetailsDTO(pharmacist)));

        return new ResponseEntity<>(pharmacistsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "search")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMINISTRATOR')")
    public ResponseEntity<List<PharmacistDetailsDTO>> search(@AuthenticationPrincipal User user, @RequestParam String firstName, @RequestParam String lastName){
        List<Pharmacist> pharmacists = pharmacistService.search(user, firstName, lastName);
        List<PharmacistDetailsDTO> pharmacistDetailsDTO = new ArrayList<>();
        pharmacists.forEach(pharmacist -> pharmacistDetailsDTO.add(new PharmacistDetailsDTO(pharmacist)));
        return new ResponseEntity<>(pharmacistDetailsDTO, HttpStatus.OK);
    }


    @GetMapping(value = "pharmacy/{id}")
    public ResponseEntity<List<EmployeeBasicDTO>> getWorkingPharmacistsAtPharmacy(@PathVariable Long id){
        List<Pharmacist> pharmacists = pharmacistService.getWorkingPharmacistsAtPharmacy(id);
        List<EmployeeBasicDTO> employees = new ArrayList<>();
        pharmacists.forEach( pharmacist -> employees.add(new EmployeeBasicDTO(pharmacist)));
        return new ResponseEntity<>(employees, pharmacists.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "pharmacist-information")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<PharmacistDTO> getPharmacyForPharmacist(HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            PharmacistDTO pharmacistDTO = new PharmacistDTO(user.getFirstName(), user.getLastName(),
                    pharmacistService.getPharmacyForPharmacist(user.getId()).getName());
            return ResponseEntity.ok().body(pharmacistDTO);
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/patients-for-pharmacist")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<UserSearchResult>> getPatientsForPharmacist(@AuthenticationPrincipal User user){
        List<UserSearchResult> converted = new ArrayList<>();
        appointmentService.extractPatientsFromCounselings((Pharmacist) user).forEach(patient->
            converted.add(new UserSearchResult(patient, 0))
        );
        return new ResponseEntity<>(converted, HttpStatus.OK);
    }

}
