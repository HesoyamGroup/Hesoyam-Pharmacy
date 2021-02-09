package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.model.MedicineFeedback;

public interface IMedicineFeedbackService {

    MedicineFeedback findByMedicineIdAndPatientId(Long medicineId, Long patientId);
    MedicineFeedback create(MedicineFeedback medicineFeedback);
    void update(MedicineFeedback medicineFeedback);
    double calculateMedicineRating(Long id);

}
