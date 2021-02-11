package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.dto.PatientDTO;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.util.search.UserSearchResult;

import java.util.List;

public interface IAppointmentService {
    int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist);
    int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist);
    List<Counseling> getCounselingsForPharmacist(DateTimeRange dateTimeRange, Pharmacist pharmacist);
    List<CheckUp> getCheckUpsForDermatologist(DateTimeRange dateTimeRange, Dermatologist dermatologist);
    List<PatientDTO> extractPatientsFromCheckups(Dermatologist dermatologist);
    List<PatientDTO> extractPatientsFromCounselings(Pharmacist pharmacist);
    int getCompletedAppointmentsCountInPharmacyByPatient(Pharmacy pharmacy, Patient patient);
    List<UserSearchResult> searchUsers(Employee user, String query);
    boolean checkNewAppointment(User user, Patient patient, DateTimeRange range);
    Appointment createNewAppointment(Patient patient, Employee employee, long pharmacyId, DateTimeRange range, double price);
}
