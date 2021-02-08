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
    public List<EmployeeFeedback> findByEmployeeId(Long employeeId) {
        return employeeFeedbackRepository.findAllByEmployee_Id(employeeId);
    }

    @Override
    public List<EmployeeFeedback> getAll() {
        return employeeFeedbackRepository.findAll();
    }

    @Override
    public void update(EmployeeFeedback employeeFeedbackData) {
        EmployeeFeedback employeeFeedback = employeeFeedbackRepository.getOne(employeeFeedbackData.getId());

        employeeFeedback.update(employeeFeedback);

        employeeFeedbackRepository.save(employeeFeedback);
    }

    @Override
    public double calculateEmployeeRating(Long id) {
        List<EmployeeFeedback> employeeFeedbacks = employeeFeedbackRepository.findAllByEmployee_Id(id);

        double ratingSum = 0;
        for(EmployeeFeedback ef: employeeFeedbacks){
            ratingSum += ef.getRating();
        }
        return ratingSum/employeeFeedbacks.size();
    }
}
