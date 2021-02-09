package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.MedicineFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineFeedbackRepository extends JpaRepository<MedicineFeedback, Long> {

    MedicineFeedback findByMedicine_IdAndPatient_Id(Long medicineId, Long patientId);
    List<MedicineFeedback> findAllByMedicine_Id(Long id);
}
