package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.user.dto.*;
import com.hesoyam.pharmacy.user.mapper.DermatologistMapper;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistController {
    @Autowired
    private IDermatologistService dermatologistService;

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMINISTRATOR')")
    public ResponseEntity<List<DermatologistDetailsDTO>> getAllDermatologists(@AuthenticationPrincipal User user){
        List<Dermatologist> dermatologists = dermatologistService.getAll(user);
        List<DermatologistDetailsDTO> dermatologistsDTO = new ArrayList<>();
        dermatologists.forEach(dermatologist -> dermatologistsDTO.add(new DermatologistDetailsDTO(dermatologist)));

        return new ResponseEntity<>(dermatologistsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/unsorted")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<EmployeeBasicDTO>> getUnsortedDermatologistsByAdministrator(@AuthenticationPrincipal User user){
        List<Dermatologist> dermatologists = dermatologistService.getUnsortedDermatologistsByAdministrator(user);
        return ResponseEntity.ok().body(DermatologistMapper.getBasicInfoList(dermatologists));
    }

    @GetMapping(value = "/patients-for-dermatologist")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<List<PatientDTO>> getPatientsForDermatologist(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(appointmentService.extractPatientsFromCheckups((Dermatologist) user), HttpStatus.OK);
    }

    @GetMapping(value = "search")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMINISTRATOR')")
    public ResponseEntity<List<DermatologistDetailsDTO>> search(@AuthenticationPrincipal User user, @RequestParam String firstName, @RequestParam String lastName){
        List<Dermatologist> dermatologists = dermatologistService.search(user, firstName, lastName);
        List<DermatologistDetailsDTO> dermatologistDetailsDTO = new ArrayList<>();
        dermatologists.forEach(dermatologist -> dermatologistDetailsDTO.add(new DermatologistDetailsDTO(dermatologist)));
        return new ResponseEntity<>(dermatologistDetailsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "pharmacy/{id}")
    public ResponseEntity<List<EmployeeBasicDTO>> getWorkingDermatologistsAtPharmacy(@PathVariable Long id){
        List<Dermatologist> dermatologists = dermatologistService.getWorkingDermatologistsAtPharmacy(id);
        List<EmployeeBasicDTO> employees = new ArrayList<>();
        dermatologists.forEach( dermatologist -> employees.add(new EmployeeBasicDTO(dermatologist)));
        return new ResponseEntity<>(employees, dermatologists.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "dermatologist-information")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<EmployeeBasicDTO> getDermatologistInformation(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(new EmployeeBasicDTO((Employee) user), HttpStatus.OK);
    }

    @GetMapping(value = "dermatologist-information-edit")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<DermatologistBasicInformationDTO> getDermatologistEditableInformation(@AuthenticationPrincipal User
                                                                                                       user){
        return new ResponseEntity<>(new DermatologistBasicInformationDTO((Dermatologist) user), HttpStatus.OK);
    }

    @PutMapping(value = "/add-to-my-pharmacy", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<EmployeeBasicDTO> addDermatologistToAdministratorPharmacy(@AuthenticationPrincipal User user, @RequestBody @Valid DermatologistAddPharmacyDTO dermatologist){
        try{
            Dermatologist addedDermatologist = dermatologistService.addDermatologistToAdministratorPharmacy(user, dermatologist);
            return ResponseEntity.ok().body(new EmployeeBasicDTO(addedDermatologist));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch(IllegalAccessException e){
            return ResponseEntity.badRequest().build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> removeFromPharmacy(@PathVariable Long id, @AuthenticationPrincipal User user){
        try{
            dermatologistService.removeFromPharmacy(id, user);
            return ResponseEntity.ok().build();
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch(IllegalAccessException e){
            return ResponseEntity.badRequest().build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
