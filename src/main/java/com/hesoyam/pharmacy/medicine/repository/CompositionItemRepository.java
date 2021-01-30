package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.CompositionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionItemRepository extends JpaRepository<CompositionItem, Long> {
}
