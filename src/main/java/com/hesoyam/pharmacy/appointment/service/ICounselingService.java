package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;

import java.time.LocalDateTime;
import java.util.List;

public interface ICounselingService {
    Counseling updateCounselingAfterAppointment(long patientId, LocalDateTime from, String report, Pharmacist pharmacist) throws CounselingNotFoundException;

    List<Counseling> getAllCounselingsForPatientAndPharmacist(Patient patient, Pharmacist user);

    Counseling cancelCounseling(Patient patient, LocalDateTime from, Pharmacist pharmacist);
}
