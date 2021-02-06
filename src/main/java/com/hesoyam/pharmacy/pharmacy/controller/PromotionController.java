package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.PromotionDTO;
import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import com.hesoyam.pharmacy.pharmacy.service.IPromotionService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(value = "")
    public ResponseEntity<List<PromotionDTO>> getAllPromotions(@AuthenticationPrincipal User user){

        try {
            List<Promotion> promotions = promotionService.getAllByAdministratorPharmacy(user);
            List<PromotionDTO> promotionsDTO = new ArrayList<>();
            promotions.forEach(promotion -> promotionsDTO.add(new PromotionDTO(promotion)));
            return ResponseEntity.status(HttpStatus.OK).body(promotionsDTO);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PromotionDTO> create(@AuthenticationPrincipal User user, @RequestBody PromotionDTO candidatePromotion){
        try{
            Promotion promotion = promotionService.create(user, candidatePromotion);
            return ResponseEntity.status(HttpStatus.CREATED).body(new PromotionDTO(promotion));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
