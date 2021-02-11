package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    int countAppointmentsByPatientAndAppointmentStatusAndPharmacy(Patient patient, AppointmentStatus appointmentStatus, Pharmacy pharmacy);

    @Lock(LockModeType.PESSIMISTIC_READ)
    int countAppointmentsByPatientAndDateTimeRange_From(Patient patient, LocalDateTime range);

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Appointment> getAllByPatient(Patient patient);



}
