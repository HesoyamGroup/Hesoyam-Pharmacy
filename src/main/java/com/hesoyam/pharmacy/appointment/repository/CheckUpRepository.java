package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckUpRepository extends JpaRepository<CheckUp, Long> {
    Integer countChecksUpByPatientAndAppointmentStatusAndDermatologist(Patient patient, AppointmentStatus appointmentStatus, Dermatologist dermatologist);

    List<CheckUp> getAllByDermatologist_Id(Long id);
}
