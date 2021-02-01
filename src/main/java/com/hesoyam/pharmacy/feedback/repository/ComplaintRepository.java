package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findAllByComplaintStatus(ComplaintStatus complaintStatus);
    Complaint findComplaintByIdAndComplaintStatus(Long id, ComplaintStatus complaintStatus);
}
