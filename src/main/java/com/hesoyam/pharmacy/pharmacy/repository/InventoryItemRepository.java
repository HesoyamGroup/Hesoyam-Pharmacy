package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("select ii from Pharmacy p join p.inventory i join i.inventoryItems ii where i.id = :pharmacy_id")
    List<InventoryItem> getAllByPharmacyId(@Param("pharmacy_id") Long pharmacyId);
}
