package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
