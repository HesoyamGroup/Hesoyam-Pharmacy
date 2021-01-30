package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("select it from Inventory i join i.inventoryItems it where i.pharmacy.id = :pharmacy_id and it.medicine.id = :medicine_id")
    InventoryItem getInventoryItemByPharmacyIdAndMedicineId(@Param("pharmacy_id") Long pharmacyId, @Param("medicine_id") Long medicineId);
}
