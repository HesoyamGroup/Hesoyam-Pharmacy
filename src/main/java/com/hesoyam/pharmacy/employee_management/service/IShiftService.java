package com.hesoyam.pharmacy.employee_management.service;

import com.hesoyam.pharmacy.employee_management.model.Shift;

import java.util.List;

public interface IShiftService {
    List<Shift> getAllByEmployee(Long id);
}
