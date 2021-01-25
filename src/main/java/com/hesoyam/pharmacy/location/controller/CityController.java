package com.hesoyam.pharmacy.location.controller;

import com.hesoyam.pharmacy.location.model.City;
import com.hesoyam.pharmacy.location.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping("/within-country/{id}")
    public ResponseEntity<List<City>> getCitiesInCountry(@PathVariable Long id){
        if(id == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(cityService.getAllCitiesInCountry(id));
    }
}
