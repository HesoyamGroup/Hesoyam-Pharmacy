package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.DTO.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.DTO.PharmacistDTO;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.DTO.PharmacistDetailsDTO;
import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.dto.PharmacistDetailsDTO;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
