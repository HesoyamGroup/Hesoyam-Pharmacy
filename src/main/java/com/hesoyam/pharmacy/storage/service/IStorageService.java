package com.hesoyam.pharmacy.storage.service;

import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.user.model.Supplier;
import com.hesoyam.pharmacy.user.model.User;


public interface IStorageService {
    Storage create(Storage storage);
    Storage get(Long id);
    Storage getUserStorage(User user);
}
