package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.feedback.model.MedicineFeedback;
import com.hesoyam.pharmacy.feedback.repository.MedicineFeedbackRepository;
import com.hesoyam.pharmacy.feedback.service.IMedicineFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineFeedbackService implements IMedicineFeedbackService {

    @Autowired
    private MedicineFeedbackRepository medicineFeedbackRepository;

    @Override
    public MedicineFeedback findByMedicineIdAndPatientId(Long medicineId, Long patientId) {
        return medicineFeedbackRepository.findByMedicine_IdAndPatient_Id(medicineId, patientId);
    }

    @Override
    public MedicineFeedback create(MedicineFeedback medicineFeedback) {
        return medicineFeedbackRepository.save(medicineFeedback);
    }

    @Override
    public void update(MedicineFeedback medicineFeedbackData) {
        MedicineFeedback medicineFeedback = medicineFeedbackRepository.getOne(medicineFeedbackData.getId());

        medicineFeedback.update(medicineFeedbackData);

        medicineFeedbackRepository.save(medicineFeedback);
    }

    @Override
    public double calculateMedicineRating(Long id) {
        List<MedicineFeedback> medicineFeedbacks = medicineFeedbackRepository.findAllByMedicine_Id(id);

        double ratingSum = 0;
        for(MedicineFeedback mf: medicineFeedbacks){
            ratingSum += mf.getRating();
        }
        double averageRating = ratingSum/medicineFeedbacks.size();

        return Math.round(averageRating*100.0)/100.0;
    }
}
