package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("select ii from Pharmacy p join p.inventory i join i.inventoryItems ii where i.id = :pharmacy_id")
    List<InventoryItem> getAllByPharmacyId(@Param("pharmacy_id") Long pharmacyId);

    @Query("select it from Inventory i join i.inventoryItems it where i.pharmacy.id = :pharmacy_id and it.medicine.id = :medicine_id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    InventoryItem getInventoryItemByPharmacyIdAndMedicineId(@Param("pharmacy_id") Long pharmacyId, @Param("medicine_id") Long medicineId);

    @Query("select it from Inventory i join i.inventoryItems it where i.pharmacy.inventory.id = :inventory_id and it.medicine.id = :medicine_id")
    InventoryItem getByMedicineIdAndInventoryId(@Param("medicine_id")Long medicineId,@Param("inventory_id") Long inventoryId);


}
