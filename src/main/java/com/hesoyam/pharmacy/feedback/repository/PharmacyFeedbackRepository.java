package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.PharmacyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyFeedbackRepository extends JpaRepository<PharmacyFeedback, Long> {

    PharmacyFeedback findByPharmacy_IdAndAndPatient_Id(Long pharmacyId, Long patientId);
    List<PharmacyFeedback> findAllByPharmacy_Id(Long id);

}
