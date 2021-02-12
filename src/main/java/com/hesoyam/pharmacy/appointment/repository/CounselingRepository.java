package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {
    Integer countCounselingsByPatientAndAppointmentStatusAndPharmacist(Patient patient, AppointmentStatus appointmentStatus, Pharmacist pharmacist);

    List<Counseling> findByPharmacist(Pharmacist pharmacist);
    Counseling findByPatient_IdAndDateTimeRange_FromAndPharmacist(long patientId, LocalDateTime from, Pharmacist pharmacist);
    List<Counseling> findAllByPatientAndPharmacist(Patient patient, Pharmacist pharmacist);
    List<Counseling> findByAppointmentStatus(AppointmentStatus appointmentStatus);
    List<Counseling> findByPharmacy_IdAndAppointmentStatus(Long id, AppointmentStatus appointmentStatus);
    List<Counseling> getAllByPatient_Id(Long id);
    List<Counseling> getAllByPatient_IdAndAppointmentStatus(Long id, AppointmentStatus appointmentStatus);


    int countCounselingsByPharmacistAndDateTimeRange_From(Pharmacist user, LocalDateTime range);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Appointment> getAllByPharmacist(Pharmacist user);

    @Override
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Counseling> findById(Long id);
}
