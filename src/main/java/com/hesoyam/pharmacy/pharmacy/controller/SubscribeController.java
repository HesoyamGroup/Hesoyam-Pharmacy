package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidSubscriptionRequestException;
import com.hesoyam.pharmacy.pharmacy.service.ISubscribeService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value="/subscribe")
public class SubscribeController {

    @Autowired
    private ISubscribeService subscribeService;



    @PutMapping(value = "/sub/{id}")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<String> subscribe(@PathVariable("id")Long pharmacyId, @AuthenticationPrincipal User user){
        try{
            subscribeService.subscribe(pharmacyId, user);
            return ResponseEntity.ok("Successfully subscribed.");
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (InvalidSubscriptionRequestException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value="/unsub/{id}")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<String> unsubscribe(@PathVariable("id")Long pharmacyId, @AuthenticationPrincipal User user){
        try{
            subscribeService.unsubscribe(pharmacyId, user);
            return ResponseEntity.ok("Unsubscribed.");
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (InvalidSubscriptionRequestException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value="is-subbed/{pharmacy_id}")
    public ResponseEntity<Boolean> isSubscribed(@PathVariable("pharmacy_id") Long id,@AuthenticationPrincipal User user){
        return ResponseEntity.ok(subscribeService.isSubscribed(id, user));
    }
}
