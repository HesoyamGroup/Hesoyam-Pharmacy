package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.repository.AppointmentRepository;
import com.hesoyam.pharmacy.appointment.repository.CheckUpRepository;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.repository.TherapyRepository;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TherapyRepository therapyRepository;

    @Autowired
    private CheckUpRepository checkUpRepository;

    @Autowired
    private CounselingRepository counselingRepository;


    @Override
    public int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist) {
        return checkUpRepository.countChecksUpByPatientAndAppointmentStatusAndDermatologist(patient, AppointmentStatus.COMPLETED, dermatologist);
    }

    @Override
    public int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist) {
        return counselingRepository.countCounselingsByPatientAndAppointmentStatusAndPharmacist(patient, AppointmentStatus.COMPLETED, pharmacist);
    }

    @Override
    public int getCompletedAppointmentsCountInPharmacyByPatient(Pharmacy pharmacy, Patient patient) {
        return appointmentRepository.countAppointmentsByPatientAndAppointmentStatusAndPharmacy(patient, AppointmentStatus.COMPLETED, pharmacy);
    }
}
