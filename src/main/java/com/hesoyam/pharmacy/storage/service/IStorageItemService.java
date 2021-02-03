package com.hesoyam.pharmacy.storage.service;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.storage.dto.UpdateStorageItemDTO;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IStorageItemService {
    List<StorageItem> getStorageItemsByUserId(Long id, Integer page);
    StorageItem update(UpdateStorageItemDTO updateStorageItemDTO);
    void delete(Long itemId, User loggedUser);
    StorageItem add(Medicine medicine, User loggedUser);
}
