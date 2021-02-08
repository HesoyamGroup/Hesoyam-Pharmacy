package com.hesoyam.pharmacy.employee_management.repository;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    List<VacationRequest> findAllByStatus(VacationRequestStatus status);
}
