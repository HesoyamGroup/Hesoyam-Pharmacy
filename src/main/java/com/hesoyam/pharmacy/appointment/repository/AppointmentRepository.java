package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    int countAppointmentsByPatientAndAppointmentStatusAndPharmacy(Patient patient, AppointmentStatus appointmentStatus, Pharmacy pharmacy);

    int countAppointmentsByPatientAndDateTimeRange_From(Patient patient, LocalDateTime range);
}
