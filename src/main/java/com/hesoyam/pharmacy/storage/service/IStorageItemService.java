package com.hesoyam.pharmacy.storage.service;

import com.hesoyam.pharmacy.storage.model.StorageItem;

import java.util.List;

public interface IStorageItemService {
    List<StorageItem> getStorageItemsByUserId(Long id, Integer page);
}
