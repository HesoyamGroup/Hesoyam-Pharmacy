package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;

public interface IPatientService {
    Patient getById(Long id) throws PatientNotFoundException;
}
