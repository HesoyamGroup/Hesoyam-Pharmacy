package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
