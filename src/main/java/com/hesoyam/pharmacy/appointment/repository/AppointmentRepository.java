package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    int countAppointmentsByPatientAndAppointmentStatusAndPharmacy(Patient patient, AppointmentStatus appointmentStatus, Pharmacy pharmacy);

    Integer countAppointmentsByPatientAndDateTimeRange_From(Patient patient, LocalDateTime range);
}
