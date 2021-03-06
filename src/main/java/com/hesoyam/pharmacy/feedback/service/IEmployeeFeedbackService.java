package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;

import java.util.List;

public interface IEmployeeFeedbackService {

    EmployeeFeedback create(EmployeeFeedback employeeFeedback);
    EmployeeFeedback getByEmployeeIdAndPatientId(Long employeeId, Long patientId);
    List<EmployeeFeedback> getAll();
    void update(EmployeeFeedback employeeFeedback);
    double calculateEmployeeRating(Long id);

}
