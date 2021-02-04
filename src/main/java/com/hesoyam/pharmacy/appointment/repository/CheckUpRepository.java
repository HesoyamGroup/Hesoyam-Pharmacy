package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckUpRepository extends JpaRepository<CheckUp, Long> {
    Integer countChecksUpByPatientAndAppointmentStatusAndDermatologist(Patient patient, AppointmentStatus appointmentStatus, Dermatologist dermatologist);

    List<CheckUp> getAllByDermatologist_IdAndPharmacy_Id(Long dermatologistId, Long pharmacyId);
    List<CheckUp> getAllByDermatologist_IdAndPharmacy_IdAndAppointmentStatus(Long dermatologistId, Long pharmacyId, AppointmentStatus appointmentStatus);
    List<CheckUp> getAllByDermatologist_Id(Long dermatologistId);
    List<CheckUp> findCheckUpsByDermatologist(Dermatologist dermatologist);
    List<CheckUp> getAllByPharmacy_IdAndAppointmentStatus(Long id, AppointmentStatus appointmentStatus);

}
