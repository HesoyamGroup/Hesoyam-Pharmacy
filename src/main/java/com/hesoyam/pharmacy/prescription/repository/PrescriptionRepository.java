package com.hesoyam.pharmacy.prescription.repository;

import com.hesoyam.pharmacy.prescription.model.EPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<EPrescription, Long> {

}
