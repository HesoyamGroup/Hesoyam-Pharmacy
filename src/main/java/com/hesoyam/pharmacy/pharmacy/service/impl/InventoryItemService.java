package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.repository.InventoryItemRepository;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class InventoryItemService implements IInventoryItemService {

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItem update(InventoryItem inventoryItemData) throws EntityNotFoundException {
        InventoryItem inventoryItem = inventoryItemRepository.getOne(inventoryItemData.getId());
        if(inventoryItem == null) throw new EntityNotFoundException();
        inventoryItem.update(inventoryItemData);
        inventoryItem = inventoryItemRepository.save(inventoryItem);

        return inventoryItem;
    }

    @Override
    public InventoryItem getInventoryItemByPharmacyIdAndMedicineId(Long pharmacyId, Long medicineId){
        return inventoryItemRepository.getInventoryItemByPharmacyIdAndMedicineId(pharmacyId, medicineId);
    }
}
