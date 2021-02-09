package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.exceptions.EmployeeNotFoundException;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.repository.EmployeeRepository;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getOne(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public void updateRating(Long id, double rating) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setRating(rating);

        employeeRepository.save(employee);
    }
}
