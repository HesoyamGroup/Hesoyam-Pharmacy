package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeBasicDTO> getEmployee(@PathVariable Long id){
        try{
            Employee employee = employeeService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(new EmployeeBasicDTO(employee));
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
