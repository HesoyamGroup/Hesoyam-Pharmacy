package com.hesoyam.pharmacy.storage.service.impl;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.storage.dto.UpdateStorageItemDTO;
import com.hesoyam.pharmacy.storage.exceptions.InvalidAddInventoryItemException;
import com.hesoyam.pharmacy.storage.exceptions.InvalidDeleteStorageItemRequestException;
import com.hesoyam.pharmacy.storage.exceptions.InvalidUpdateStorageItemRequestException;
import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import com.hesoyam.pharmacy.storage.repository.StorageItemRepository;
import com.hesoyam.pharmacy.storage.service.IStorageItemService;
import com.hesoyam.pharmacy.storage.service.IStorageService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StorageItemService implements IStorageItemService {

    @Autowired
    private StorageItemRepository storageItemRepository;

    @Autowired
    private IMedicineService medicineService;

    @Autowired
    private IStorageService storageService;

    @Override
    public List<StorageItem> getStorageItemsByUserId(Long id, Integer page) {
        return storageItemRepository.getStorageItemsByUserId(id, PageRequest.of(page-1, 8));
    }

    @Override
    public StorageItem update(UpdateStorageItemDTO updateStorageItemDTO) {
        StorageItem storageItem = storageItemRepository.getStorageItemByIdAndUserId(updateStorageItemDTO.getItemId(), updateStorageItemDTO.getLoggedUser().getId());
        if(storageItem == null) throw new EntityNotFoundException();

        try{
            storageItem.setStock(updateStorageItemDTO.getNewQuantity());
            return storageItemRepository.save(storageItem);
        }catch (TransactionSystemException e){
            throw new InvalidUpdateStorageItemRequestException("Validation error occured.");
        }catch (IllegalArgumentException e){
            throw new InvalidUpdateStorageItemRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(Long itemId, User loggedUser) {
        StorageItem storageItem = storageItemRepository.getStorageItemByIdAndUserId(itemId, loggedUser.getId());
        if(storageItem == null) throw new EntityNotFoundException();

        if(storageItem.getReserved() > 0){
            throw new InvalidDeleteStorageItemRequestException("You can't delete items which are reserved.");
        }
        storageItemRepository.delete(storageItem);
    }

    @Override
    public StorageItem add(Medicine medicine, User loggedUser) {
        StorageItem storageItem = storageItemRepository.getStorageItemByMedicineIdAndUserId(medicine.getId(), loggedUser.getId());
        if(storageItem != null) throw new InvalidAddInventoryItemException("The selected medicine is already added.");
        Storage storage = storageService.getUserStorage(loggedUser);
        storageItem = new StorageItem(storage, medicine);
        return storageItemRepository.save(storageItem);
    }

    @Override
    public List<Medicine> getUnaddedMedicines(Integer page, User loggedUser) {
        return storageItemRepository.getUnaddedMedicine(loggedUser.getId(), PageRequest.of(page-1, 8));
    }

}
