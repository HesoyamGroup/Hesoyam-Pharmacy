package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from Pharmacy ph join ph.subscribedPatients p where p.id = :patient_id and ph.id = :pharmacy_id")
    Patient getSubscribedPatientByPharmacy(@Param("patient_id") Long patientId, @Param("pharmacy_id") Long pharmacyId);

    Patient findPatientByEmail(String email);

    @Query("select p from Patient p where p.penaltyPoints > 0")
    List<Patient> findAllByPenaltyPointsGreaterThanZero();
}
