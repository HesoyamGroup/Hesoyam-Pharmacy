package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.feedback.model.PharmacyFeedback;
import com.hesoyam.pharmacy.feedback.repository.PharmacyFeedbackRepository;
import com.hesoyam.pharmacy.feedback.service.IPharmacyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyFeedbackService implements IPharmacyFeedbackService {

    @Autowired
    PharmacyFeedbackRepository pharmacyFeedbackRepository;

    @Override
    public PharmacyFeedback findByPharmacyIdAndPatientId(Long pharmacyId, Long patientId) {
        return pharmacyFeedbackRepository.findByPharmacy_IdAndAndPatient_Id(pharmacyId, patientId);
    }

    @Override
    public PharmacyFeedback create(PharmacyFeedback pharmacyFeedback) {
        return pharmacyFeedbackRepository.save(pharmacyFeedback);
    }

    @Override
    public void update(PharmacyFeedback pharmacyFeedbackData) {
        PharmacyFeedback pharmacyFeedback = pharmacyFeedbackRepository.getOne(pharmacyFeedbackData.getPharmacy().getId());

        pharmacyFeedback.update(pharmacyFeedbackData);

        pharmacyFeedbackRepository.save(pharmacyFeedback);
    }

    @Override
    public double calculatePharmacyRating(Long id) {
        List<PharmacyFeedback> pharmacyFeedbacks = pharmacyFeedbackRepository.findAllByPharmacy_Id(id);

        double ratingSum = 0;
        for(PharmacyFeedback pf: pharmacyFeedbacks){
            ratingSum += pf.getRating();
        }
        double averageRating = ratingSum/pharmacyFeedbacks.size();

        return Math.round(averageRating*100.0)/100.0;
    }
}
