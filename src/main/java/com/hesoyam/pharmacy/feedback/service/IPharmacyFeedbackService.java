package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.model.PharmacyFeedback;

public interface IPharmacyFeedbackService {

    PharmacyFeedback findByPharmacyIdAndPatientId(Long pharmacyId, Long patientId);
    PharmacyFeedback create(PharmacyFeedback pharmacyFeedback);
    void update(PharmacyFeedback pharmacyFeedback);
    double calculatePharmacyRating(Long id);
}
