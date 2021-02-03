package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.DTO.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    private IOrderService orderService;


    @GetMapping("/active")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<List<ShowOrdersDTO>> getAllActive(@RequestParam(required = false)Integer page, @AuthenticationPrincipal User user){
        page = (page == null ? 1 : page);
        return ResponseEntity.ok(orderService.getActiveForSupplier(page, user));
    }
}
