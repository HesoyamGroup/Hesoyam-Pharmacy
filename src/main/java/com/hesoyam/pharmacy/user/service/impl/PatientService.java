package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.repository.MedicineRepository;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.repository.PatientRepository;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Patient getById(Long id) throws PatientNotFoundException {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public Boolean isPatientSubscribedToPharmacy(Long patientId, Long pharmacyId) {
        Patient p = patientRepository.getSubscribedPatientByPharmacy(patientId, pharmacyId);
        return p != null;
    }

    @Override
    public List<Medicine> getAllergiesByPatientId(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
        return patient.getAllergies();
    }

    @Override
    public List<Medicine> getMedicineNotAllergicToByPatientId(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
        List<Medicine> notAllergicTo = medicineRepository.findAll();

        if(!patient.getAllergies().isEmpty()){
            for(Medicine m: patient.getAllergies()){
                if(notAllergicTo.contains(m)){
                    notAllergicTo.remove(m);
                }
            }
        }

        return notAllergicTo;
    }

    @Override
    public Patient update(Patient patientData) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientData.getId()).orElseThrow(() -> new PatientNotFoundException(patientData.getId()));
        patient = patientRepository.save(patientData);

        return patient;
    }

    @Override
    public Patient getByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    @Override
    public Patient penalizeForMissingAppointment(Patient patient) {
        patient.setPenaltyPoints(patient.getPenaltyPoints() + 1);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllWithMoreThanZeroPenaltyPoints() {
        return patientRepository.findAllByPenaltyPointsGreaterThanZero();
    }

}
