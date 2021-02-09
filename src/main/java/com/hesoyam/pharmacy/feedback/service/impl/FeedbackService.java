package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.feedback.model.Feedback;
import com.hesoyam.pharmacy.feedback.repository.FeedbackRepository;
import com.hesoyam.pharmacy.feedback.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findByPatientId(Long id) {
        return feedbackRepository.findAllByPatient_Id(id);
    }
}
