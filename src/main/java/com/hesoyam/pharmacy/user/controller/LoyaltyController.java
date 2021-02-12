package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.DTO.PatientLoyaltyProgramDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidDeleteRequest;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/loyalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyController {

    @Autowired
    private ILoyaltyAccountService loyaltyAccountService;

    @Autowired
    private IPatientService patientService;

    @GetMapping("/membership/all")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<List<LoyaltyAccountMembership>> getAllLoyaltyAccountMemberships(){
        return ResponseEntity.ok(loyaltyAccountService.getAllLoyaltyAccountMemberships());
    }

    @GetMapping("/membership/config/all")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<List<LoyaltyProgramConfig>> getLoyaltyProgramConfig(){
        return ResponseEntity.ok(loyaltyAccountService.getAllLoyaltyProgramConfig());
    }

    @PostMapping("/membership/config/update")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<LoyaltyProgramConfig> update(@RequestBody @Valid LoyaltyProgramConfigUpdateDTO loyaltyProgramConfigUpdateDTO){
        try{
            return ResponseEntity.ok(loyaltyAccountService.updateLoyaltyProgramConfig(loyaltyProgramConfigUpdateDTO));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.notFound().build();
        }catch (ObjectOptimisticLockingFailureException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @PostMapping("/membership/update")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<LoyaltyAccountMembership> update(@RequestBody @Valid LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO){
        try{
            return ResponseEntity.ok(loyaltyAccountService.updateLoyaltyAccountMembership(loyaltyAccountMembershipDTO));
        } catch (LoyaltyAccountMembershipInvalidUpdateException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ObjectOptimisticLockingFailureException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @PostMapping("/membership/create")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<LoyaltyAccountMembership> create(@RequestBody @Valid LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO){
        try{
            return ResponseEntity.ok(loyaltyAccountService.createLoyaltyAccountMembership(loyaltyAccountMembershipDTO));
        } catch (LoyaltyAccountMembershipInvalidUpdateException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/membership/delete/{id}")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity delete(@PathVariable("id")Long id){
        try{
            loyaltyAccountService.deleteLoyaltyAccountMembership(id);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (LoyaltyAccountMembershipInvalidDeleteRequest e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(){
        loyaltyAccountService.refreshLoyaltyAccounts();
        return ResponseEntity.ok().build();
    }

    //@Secured("ROLE_PATIENT")
    @GetMapping(value = "/patient")
    public ResponseEntity<PatientLoyaltyProgramDTO> getPatientLoyaltyProgram(@AuthenticationPrincipal User user){

        LoyaltyAccount loyaltyAccount = loyaltyAccountService.getPatientLoyaltyAccountByPatientId(user.getId());

        try{
            Patient patient = patientService.getById(user.getId());

            PatientLoyaltyProgramDTO patientLoyaltyProgramDTO = new PatientLoyaltyProgramDTO(loyaltyAccount, patient.getPenaltyPoints());

            return ResponseEntity.ok().body(patientLoyaltyProgramDTO);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PatientLoyaltyProgramDTO(loyaltyAccount));
        }


    }

}
