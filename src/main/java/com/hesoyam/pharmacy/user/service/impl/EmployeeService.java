package com.hesoyam.pharmacy.user.service.impl;

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
}
