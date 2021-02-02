package com.hesoyam.pharmacy.storage.controller;

import com.hesoyam.pharmacy.storage.dto.UpdateStorageItemDTO;
import com.hesoyam.pharmacy.storage.exceptions.InvalidDeleteStorageItemRequestException;
import com.hesoyam.pharmacy.storage.exceptions.InvalidUpdateStorageItemRequestException;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.storage.service.IStorageItemService;
import com.hesoyam.pharmacy.storage.service.IStorageService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value="/storage", produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IStorageItemService storageItemService;

    @GetMapping("/my")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<List<StorageItem>> getStorageItemsByStorageId(@RequestParam(required = false) Integer page, @AuthenticationPrincipal User user){
        try{
            if(page == null || page < 1) page = 1;
            return ResponseEntity.ok(storageItemService.getStorageItemsByUserId(user.getId(), page));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/my/update")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<StorageItem> updateItemStock(@RequestBody UpdateStorageItemDTO updateStorageItemDTO, @AuthenticationPrincipal User user){
        updateStorageItemDTO.setLoggedUser(user);
        try{
            return ResponseEntity.ok(storageItemService.update(updateStorageItemDTO));
        }catch (InvalidUpdateStorageItemRequestException e){
            return ResponseEntity.badRequest().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/my/delete/{id}")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity deleteStorageItem(@PathVariable("id")Long itemId, @AuthenticationPrincipal User user){
        try{
            storageItemService.delete(itemId, user);
            return ResponseEntity.ok().build();
        }catch (InvalidDeleteStorageItemRequestException e){
            return ResponseEntity.badRequest().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
