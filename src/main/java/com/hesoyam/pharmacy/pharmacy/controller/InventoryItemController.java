package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemReservationDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/inventory-item", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemController {

    @Autowired
    IInventoryItemService inventoryItemService;

    @Autowired
    IUserService userService;

    @Autowired
    private TokenUtils tokenUtils;



    @PostMapping("/reserve-inventory-item")
    public ResponseEntity<InventoryItem> reserveInventoryItem(@RequestBody InventoryItemReservationDTO inventoryItemReservationDTO, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(email);
        } catch (UserNotFoundException e) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        try {
            InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(inventoryItemReservationDTO.getPharmacyId(), inventoryItemReservationDTO.getMedicineId());

            inventoryItem.setReserved(inventoryItem.getReserved() + 1);
            inventoryItem.setAvailable(inventoryItem.getAvailable() - 1);

            inventoryItemService.update(inventoryItem);

            return ResponseEntity.ok().body(inventoryItem);
        } catch (EntityNotFoundException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
