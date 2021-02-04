package com.hesoyam.pharmacy.storage.service.impl;

import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.storage.repository.StorageRepository;
import com.hesoyam.pharmacy.storage.service.IStorageItemService;
import com.hesoyam.pharmacy.storage.service.IStorageService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService implements IStorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage create(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Storage get(Long id) {
        return storageRepository.getOne(id);
    }

    @Override
    public Storage getUserStorage(User user) {
        return storageRepository.getStorageBySupplier_Id(user.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Storage update(Storage storage) {
        return storageRepository.save(storage);
    }


}
