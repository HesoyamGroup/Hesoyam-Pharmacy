package com.hesoyam.pharmacy.feedback.repository;

import com.hesoyam.pharmacy.feedback.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
