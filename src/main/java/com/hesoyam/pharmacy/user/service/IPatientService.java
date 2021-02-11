package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;

import java.util.List;

public interface IPatientService {
    Patient getById(Long id) throws PatientNotFoundException;
    Boolean isPatientSubscribedToPharmacy(Long patientId, Long pharmacyId);
    List<Medicine> getAllergiesByPatientId(Long id) throws PatientNotFoundException;
    List<Medicine> getMedicineNotAllergicToByPatientId(Long id) throws PatientNotFoundException;
    Patient update(Patient patient) throws PatientNotFoundException;
    Patient getByEmail(String email);
    Patient penalizeForMissingAppointment(Patient patient);
    List<Patient> getAllWithMoreThanZeroPenaltyPoints();

}
