package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.DTO.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferFilterCriteria;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidCreateOfferException;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidEditOfferException;
import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.service.IOfferService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    @Autowired
    private IOfferService offerService;


    @PostMapping("/create")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody CreateOfferDTO createOfferDTO, @AuthenticationPrincipal User user){
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

    @GetMapping("/my")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<List<OfferDTO>> getMyOffers(OfferFilterCriteria offerFilterCriteria, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(offerService.getUserOffers(offerFilterCriteria, user));
    }

    @PutMapping("/cancel/{id}")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<OfferDTO> cancelOffer(@PathVariable("id")Long id, @AuthenticationPrincipal User user){
        try{
            return ResponseEntity.ok(offerService.cancel(id, user));
        }catch (InvalidEditOfferException e){
            return ResponseEntity.badRequest().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
