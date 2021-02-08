package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeFeedbackRepository extends JpaRepository<EmployeeFeedback, Long> {

    List<EmployeeFeedback> findAllByEmployee_Id(Long id);

}
