package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.exceptions.EmployeeNotFoundException;
import com.hesoyam.pharmacy.user.model.Employee;

public interface IEmployeeService {
    Employee getOne(Long id);
    void updateRating(Long id, double rating) throws EmployeeNotFoundException;
}
