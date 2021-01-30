package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.model.Contraindication;
import com.hesoyam.pharmacy.medicine.service.IContraindicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contraindication")
public class ContraindicationController {

    @Autowired
    private IContraindicationService contraindicationService;


    @GetMapping("/all")
    public ResponseEntity<List<Contraindication>> getAll(){
        return ResponseEntity.ok(contraindicationService.getAll());
    }
}
