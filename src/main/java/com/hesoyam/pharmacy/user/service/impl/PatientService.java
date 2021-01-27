package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.repository.PatientRepository;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient getById(Long id) throws PatientNotFoundException {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

}
