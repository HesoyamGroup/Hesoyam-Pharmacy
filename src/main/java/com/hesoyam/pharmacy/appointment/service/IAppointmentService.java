package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.util.DateTimeRange;

import java.util.List;

public interface IAppointmentService {
    int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist);
    int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist);
    List<Counseling> getCounselingsForPharmacist(DateTimeRange dateTimeRange, Pharmacist pharmacist);
}
