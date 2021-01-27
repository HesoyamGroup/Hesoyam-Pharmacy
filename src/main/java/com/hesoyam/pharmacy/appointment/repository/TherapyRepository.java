package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {
}
