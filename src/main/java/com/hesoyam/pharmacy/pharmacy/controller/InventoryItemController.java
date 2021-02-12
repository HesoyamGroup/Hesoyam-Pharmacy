package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemDTO;
import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemReservationDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/inventory-item", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemController {
    @Autowired
    private IInventoryItemService inventoryItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "/{id}/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InventoryItemPriceDTO>> createInventoryItemPrice(@AuthenticationPrincipal User user, @RequestBody @Valid InventoryItemPriceDTO itemPriceDTO, @PathVariable Long id){
        try {
            List<InventoryItemPrice> newItemPrices = inventoryItemService.create(id, itemPriceDTO, user);
            if(newItemPrices == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            else{
                List<InventoryItemPriceDTO> itemPricesDTO = new ArrayList<>();
                newItemPrices.forEach(itemPrice -> itemPricesDTO.add(new InventoryItemPriceDTO(itemPrice)));
                return ResponseEntity.status(HttpStatus.CREATED).body(itemPricesDTO);
            }
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/{id}/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InventoryItemPriceDTO>> updateInventoryItemPrice(@AuthenticationPrincipal User user, @RequestBody @Valid InventoryItemPriceDTO itemPriceDTO, @PathVariable Long id){
        try {
            List<InventoryItemPrice> updatedItemPrices = inventoryItemService.update(id, itemPriceDTO, user);
            if(updatedItemPrices == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            else {
                List<InventoryItemPriceDTO> itemPricesDTO = new ArrayList<>();
                updatedItemPrices.forEach(itemPrice -> itemPricesDTO.add(new InventoryItemPriceDTO(itemPrice)));
                return ResponseEntity.status(HttpStatus.OK).body(itemPricesDTO);
            }
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/pharmacy/{id}")
    public ResponseEntity<List<InventoryItemDTO>> getInventoryItemsByPharmacy(@PathVariable Long id) {
        List<InventoryItem> inventoryItems = inventoryItemService.getAllByPharmacy(id);
        List<InventoryItemDTO> inventoryItemsDTO = new ArrayList<>();
        inventoryItems.forEach(inventoryItem -> inventoryItemsDTO.add(new InventoryItemDTO(inventoryItem)));

        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemsDTO);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user, @PathVariable Long id){
        try{
            inventoryItemService.delete(user, id);
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/reserve-inventory-item")
    public ResponseEntity<InventoryItem> reserveInventoryItem(@RequestBody InventoryItemReservationDTO inventoryItemReservationDTO, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getUsernameFromToken(token);

        try{
            userService.findByEmail(email);
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
