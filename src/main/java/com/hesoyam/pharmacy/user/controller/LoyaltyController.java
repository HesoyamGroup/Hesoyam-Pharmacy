package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/loyalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyController {

    @Autowired
    private ILoyaltyAccountService loyaltyAccountService;

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


}
