package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.model.Manufacturer;
import com.hesoyam.pharmacy.medicine.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    private IManufacturerService manufacturerService;

    @GetMapping("/all")
    public ResponseEntity<List<Manufacturer>> getAllManufacters() {
        return ResponseEntity.ok(manufacturerService.getAll());
    }

    @PostMapping("/create")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Manufacturer> create(@RequestBody(required = true) @Valid Manufacturer manufacturer){
        return ResponseEntity.ok(manufacturerService.create(manufacturer));
    }
}
