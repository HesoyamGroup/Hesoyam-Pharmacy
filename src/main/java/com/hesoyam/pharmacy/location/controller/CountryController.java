package com.hesoyam.pharmacy.location.controller;

import com.hesoyam.pharmacy.location.model.Country;
import com.hesoyam.pharmacy.location.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Country>> getAllCountries(){
        return ResponseEntity.ok().body(countryService.getAll());
    }
}
