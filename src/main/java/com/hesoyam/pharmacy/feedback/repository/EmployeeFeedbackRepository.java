package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeFeedbackRepository extends JpaRepository<EmployeeFeedback, Long> {

    List<EmployeeFeedback> findAllByEmployee_Id(Long id);

    EmployeeFeedback findAllByEmployee_IdAndPatient_Id(Long employeeId, Long patientId);

}
