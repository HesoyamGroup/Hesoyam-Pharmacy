package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {
    Integer countCounselingsByPatientAndAppointmentStatusAndPharmacist(Patient patient, AppointmentStatus appointmentStatus, Pharmacist pharmacist);

    List<Counseling> findByPharmacist(Pharmacist pharmacist);
    List<Counseling> findByAppointmentStatus(AppointmentStatus appointmentStatus);
    List<Counseling> findByPharmacy_IdAndAppointmentStatus(Long id, AppointmentStatus appointmentStatus);
}
