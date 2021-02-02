package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.model.CompositionItem;
import com.hesoyam.pharmacy.medicine.service.ICompositionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/composition-item")
public class CompositionItemController {
    @Autowired
    private ICompositionItemService compositionItemService;


    @GetMapping("/all")
    public List<CompositionItem> getAll(){
        return compositionItemService.getAll();
    }
}
