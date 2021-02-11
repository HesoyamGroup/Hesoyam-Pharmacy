package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findAllByComplaintStatus(ComplaintStatus complaintStatus);
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Complaint findComplaintByIdAndComplaintStatus(Long id, ComplaintStatus complaintStatus);
}
