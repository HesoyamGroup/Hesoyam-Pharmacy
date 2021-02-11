package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Override
    Inventory getOne(Long id);

}
