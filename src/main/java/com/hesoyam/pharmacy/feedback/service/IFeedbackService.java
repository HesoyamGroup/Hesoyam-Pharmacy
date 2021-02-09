package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.model.Feedback;

import java.util.List;

public interface IFeedbackService {

    List<Feedback> findByPatientId(Long id);
}
