package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;


public interface IInventoryItemService {
    List<InventoryItemPrice> create(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User loggedInUser) throws IllegalAccessException;

    List<InventoryItemPrice> update(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User user) throws IllegalAccessException;

    List<InventoryItem> getAllByPharmacy(Long pharmacyId);

    public InventoryItem update(InventoryItem inventoryItem) throws EntityNotFoundException;
    InventoryItem getInventoryItemByPharmacyIdAndMedicineId(Long pharmacyId, Long medicineId) throws EntityNotFoundException;
}
