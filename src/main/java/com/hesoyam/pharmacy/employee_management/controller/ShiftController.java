package com.hesoyam.pharmacy.employee_management.controller;

import com.hesoyam.pharmacy.employee_management.dto.ShiftDTO;
import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.service.IShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/shift", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShiftController {

    @Autowired
    private IShiftService shiftService;

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<List<ShiftDTO>> getShiftsByEmployee(@PathVariable Long id){
        List<Shift> shifts = shiftService.getAllByEmployee(id);
        List<ShiftDTO> shiftsDTO = new ArrayList<>();
        shifts.forEach(shift -> shiftsDTO.add(new ShiftDTO(shift)));
        return ResponseEntity.status(HttpStatus.OK).body(shiftsDTO);
    }
}
