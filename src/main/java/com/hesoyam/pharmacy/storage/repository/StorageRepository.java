package com.hesoyam.pharmacy.storage.repository;

import com.hesoyam.pharmacy.storage.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage getStorageBySupplier_Id(Long id);
}
