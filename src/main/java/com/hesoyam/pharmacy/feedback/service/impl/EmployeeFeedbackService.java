package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import com.hesoyam.pharmacy.feedback.repository.EmployeeFeedbackRepository;
import com.hesoyam.pharmacy.feedback.service.IEmployeeFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeFeedbackService implements IEmployeeFeedbackService {

    @Autowired
    private EmployeeFeedbackRepository employeeFeedbackRepository;

    @Override
    public EmployeeFeedback create(EmployeeFeedback employeeFeedback) {
        return employeeFeedbackRepository.save(employeeFeedback);
    }

    @Override
    public EmployeeFeedback getByEmployeeIdAndPatientId(Long employeeId, Long patientId) {
        return employeeFeedbackRepository.findAllByEmployee_IdAndPatient_Id(employeeId, patientId);
    }

    @Override
    public List<EmployeeFeedback> getAll() {
        return employeeFeedbackRepository.findAll();
    }

    @Override
    public void update(EmployeeFeedback employeeFeedbackData) {
        EmployeeFeedback employeeFeedback = employeeFeedbackRepository.getOne(employeeFeedbackData.getId());

        employeeFeedback.update(employeeFeedbackData);

        employeeFeedbackRepository.save(employeeFeedback);
    }

    @Override
    public double calculateEmployeeRating(Long id) {
        List<EmployeeFeedback> employeeFeedbacks = employeeFeedbackRepository.findAllByEmployee_Id(id);

        double ratingSum = 0;
        for(EmployeeFeedback ef: employeeFeedbacks){
            ratingSum += ef.getRating();
        }
        double averageRating = ratingSum/employeeFeedbacks.size();

        return Math.round(averageRating * 100.0) / 100.0;
    }
}
