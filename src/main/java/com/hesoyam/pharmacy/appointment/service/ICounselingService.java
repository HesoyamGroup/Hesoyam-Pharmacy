package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;

import java.time.LocalDateTime;
import java.util.List;

public interface ICounselingService {
    void updateCounselingAfterAppointment(long patientId, LocalDateTime from, String report, Pharmacist pharmacist) throws CounselingNotFoundException;

    List<Counseling> getAllCounselingsForPatientAndPharmacist(Patient patient, Pharmacist user);
}
