package com.hesoyam.pharmacy.storage.repository;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageItemRepository extends JpaRepository<StorageItem, Long> {
    @Query("SELECT storageitem FROM Storage storage JOIN StorageItem storageitem ON storage = storageitem.storage AND storage.supplier.id = :id ORDER BY storageitem.medicine.name")
    List<StorageItem> getStorageItemsByUserId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT storageitem FROM StorageItem storageitem WHERE storageitem.storage.supplier.id = :userId AND storageitem.id=:itemId")
    StorageItem getStorageItemByIdAndUserId(@Param("itemId") Long itemId, @Param("userId") Long userId);

    @Query("SELECT storageitem from StorageItem storageitem WHERE storageitem.storage.supplier.id = :userId AND storageitem.medicine.id=:medicineId")
    StorageItem getStorageItemByMedicineIdAndUserId(@Param("medicineId") Long medicineId, @Param("userId") Long userId);

    @Query("SELECT medicine FROM Medicine medicine " +
            "WHERE medicine.id NOT IN (" +
            "SELECT storageitem.medicine.id from StorageItem storageitem WHERE storageitem.storage.supplier.id = :userId)")
    List<Medicine> getUnaddedMedicine(@Param("userId") Long userId, Pageable pageable);

    List<StorageItem> getStorageItemByMedicine(Medicine medicine);

}
