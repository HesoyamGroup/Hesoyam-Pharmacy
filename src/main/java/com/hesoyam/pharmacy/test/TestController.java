package com.hesoyam.pharmacy.test;

import com.hesoyam.pharmacy.location.model.Country;
import com.hesoyam.pharmacy.location.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("Hesoyam Pharmacy is up and running :)");
    }

    @GetMapping("/db")
    public ResponseEntity<String> testWithDb(){
        try {
            List<Country> countries = countryService.getAll();
            return ResponseEntity.ok().body("List of countries: " + countries.stream().map(Country::getCountryName).collect(Collectors.joining(", ")));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error");
        }
    }
}
