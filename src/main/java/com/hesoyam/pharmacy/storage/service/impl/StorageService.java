package com.hesoyam.pharmacy.storage.service.impl;

import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.storage.repository.StorageRepository;
import com.hesoyam.pharmacy.storage.service.IStorageItemService;
import com.hesoyam.pharmacy.storage.service.IStorageService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements IStorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage create(Storage storage) {
        return storageRepository.save(storage);
    }



}
