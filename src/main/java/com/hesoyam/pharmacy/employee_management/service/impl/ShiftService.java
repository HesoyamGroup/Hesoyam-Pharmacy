package com.hesoyam.pharmacy.employee_management.service.impl;

import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.repository.ShiftRepository;
import com.hesoyam.pharmacy.employee_management.service.IShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService implements IShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public List<Shift> getAllByEmployee(Long id) {
        return shiftRepository.getAllByEmployee(id);
    }


}
