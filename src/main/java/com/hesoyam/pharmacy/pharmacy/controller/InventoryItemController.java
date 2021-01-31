package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.DTO.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/inventory-item", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemController {
    @Autowired
    private IInventoryItemService inventoryItemService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InventoryItemPriceDTO>> createInventoryItemPrice(@AuthenticationPrincipal User user, @RequestBody @Valid InventoryItemPriceDTO itemPriceDTO){
        try {
            List<InventoryItemPrice> newItemPrices = inventoryItemService.create(itemPriceDTO, user);
            if(newItemPrices == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            else{
                List<InventoryItemPriceDTO> itemPricesDTO = new ArrayList<>();
                newItemPrices.forEach(itemPrice -> itemPricesDTO.add(new InventoryItemPriceDTO(itemPrice, itemPriceDTO.getInventoryItemId())));
                return ResponseEntity.status(HttpStatus.CREATED).body(itemPricesDTO);
            }
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InventoryItemPriceDTO>> updateInventoryItemPrice(@AuthenticationPrincipal User user, @RequestBody @Valid InventoryItemPriceDTO itemPriceDTO){
        try {
            List<InventoryItemPrice> updatedItemPrices = inventoryItemService.update(itemPriceDTO, user);
            if(updatedItemPrices == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            else {
                List<InventoryItemPriceDTO> itemPricesDTO = new ArrayList<>();
                updatedItemPrices.forEach(itemPrice -> itemPricesDTO.add(new InventoryItemPriceDTO(itemPrice, itemPriceDTO.getInventoryItemId())));
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
}
