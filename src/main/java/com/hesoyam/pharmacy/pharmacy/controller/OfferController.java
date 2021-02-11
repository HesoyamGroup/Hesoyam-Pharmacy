package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferFilterCriteria;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidCreateOfferException;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidEditOfferException;
import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.service.IOfferService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
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

    @PutMapping("/accept/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<OfferDTO>> acceptOffer(@AuthenticationPrincipal User user, @PathVariable Long id){
        try{
            List<Offer> orderOffers = offerService.accept(user, id);
            List<OfferDTO> offersDTO = new ArrayList<>();
            orderOffers.forEach(offer -> offersDTO.add(new OfferDTO(offer)));
            return ResponseEntity.ok().body(offersDTO);
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (ObjectOptimisticLockingFailureException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
