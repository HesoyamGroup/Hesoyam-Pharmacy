package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.PriceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceItemRepository extends JpaRepository<PriceItem, Long> {
}
