package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.dto.PatientDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.util.DateTimeRange;

import java.util.List;

public interface IAppointmentService {
    int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist);
    int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist);
    List<Counseling> getCounselingsForPharmacist(DateTimeRange dateTimeRange, Pharmacist pharmacist);
    List<CheckUp> getCheckUpsForDermatologist(DateTimeRange dateTimeRange, Dermatologist dermatologist);
    List<PatientDTO> extractPatientsFromCheckups(Dermatologist dermatologist);
    int getCompletedAppointmentsCountInPharmacyByPatient(Pharmacy pharmacy, Patient patient);

}
