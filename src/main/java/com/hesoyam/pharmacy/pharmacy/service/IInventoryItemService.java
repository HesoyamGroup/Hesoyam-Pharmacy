package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;

import javax.persistence.EntityNotFoundException;

public interface IInventoryItemService {

    public InventoryItem update(InventoryItem inventoryItem) throws EntityNotFoundException;
    InventoryItem getInventoryItemByPharmacyIdAndMedicineId(Long pharmacyId, Long medicineId) throws EntityNotFoundException;
}
