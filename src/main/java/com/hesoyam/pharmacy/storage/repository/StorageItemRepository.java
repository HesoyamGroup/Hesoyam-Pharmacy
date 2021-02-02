package com.hesoyam.pharmacy.storage.repository;

import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageItemRepository extends JpaRepository<StorageItem, Long> {
    @Query("SELECT storageitem FROM Storage storage JOIN StorageItem storageitem ON storage = storageitem.storage AND storage.supplier.id = :id ORDER BY storageitem.medicine.name")
    List<StorageItem> getStorageItemsByUserId(@Param("id") Long id, Pageable pageable);
}
