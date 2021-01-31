package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;

public interface IAppointmentService {
    int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist);
    int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist);
    int getCompletedAppointmentsCountInPharmacyByPatient(Pharmacy pharmacy, Patient patient);
}
