package com.hesoyam.pharmacy.storage.service.impl;

import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.storage.repository.StorageItemRepository;
import com.hesoyam.pharmacy.storage.service.IStorageItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageItemService implements IStorageItemService {

    @Autowired
    private StorageItemRepository storageItemRepository;

    @Override
    public List<StorageItem> getStorageItemsByUserId(Long id, Integer page) {
        return storageItemRepository.getStorageItemsByUserId(id, PageRequest.of(page-1, 8));
    }
}
