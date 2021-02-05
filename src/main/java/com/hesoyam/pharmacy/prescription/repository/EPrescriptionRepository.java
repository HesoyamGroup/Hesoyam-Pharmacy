package com.hesoyam.pharmacy.prescription.repository;

import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {
    EPrescription getEPrescriptionByIdAndPatient_IdAndPrescriptionStatus(Long prescriptionId, Long patientId, PrescriptionStatus prescriptionStatus);

}
