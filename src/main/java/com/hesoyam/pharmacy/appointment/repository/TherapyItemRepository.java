package com.hesoyam.pharmacy.appointment.repository;

import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyItemRepository extends JpaRepository<TherapyItem, Long> {
}
