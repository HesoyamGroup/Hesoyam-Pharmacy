package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
