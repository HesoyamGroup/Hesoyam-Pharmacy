package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.DTO.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidCreateOfferException;
import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.service.IOfferService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    @Autowired
    private IOfferService offerService;


    @PostMapping("/create")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody CreateOfferDTO createOfferDTO, @AuthenticationPrincipal User user){
        System.out.println("*************");
        System.out.println(createOfferDTO.getDeliveryDate());
        try {
            return ResponseEntity.ok(offerService.create(createOfferDTO, user));
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }catch (InvalidCreateOfferException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
