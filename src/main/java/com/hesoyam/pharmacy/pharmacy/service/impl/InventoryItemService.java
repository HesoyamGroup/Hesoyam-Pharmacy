package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.pharmacy.repository.InventoryItemPriceRepository;
import com.hesoyam.pharmacy.pharmacy.repository.InventoryItemRepository;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InventoryItemService implements IInventoryItemService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private InventoryItemPriceRepository inventoryItemPriceRepository;


    @Override
    public List<InventoryItemPrice> create(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User loggedInUser) throws IllegalAccessException {
        InventoryItem inventoryItem = inventoryItemRepository.getOne(inventoryItemId);
        checkAdministrator(inventoryItem, loggedInUser);

        InventoryItemPrice itemPrice = new InventoryItemPrice();
        itemPrice.setPrice(itemPriceDTO.getPrice());
        itemPrice.setValidThrough(itemPriceDTO.getRange());

        boolean success = inventoryItem.addPrices(itemPrice);
        return success ? inventoryItemRepository.save(inventoryItem).getPrices() : null;
    }

    @Override
    public List<InventoryItemPrice> update(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User loggedInUser) throws IllegalAccessException {
        InventoryItem inventoryItem = inventoryItemRepository.getOne(inventoryItemId);
        checkAdministrator(inventoryItem, loggedInUser);

        InventoryItemPrice itemPrice = inventoryItemPriceRepository.getOne(itemPriceDTO.getId());
        itemPrice.setPrice(itemPriceDTO.getPrice());
        itemPrice.setValidThrough(itemPriceDTO.getRange());

        boolean success = inventoryItem.updatePrices(itemPrice);
        return success ? inventoryItemRepository.save(inventoryItem).getPrices() : null;
    }

    @Override
    public List<InventoryItem> getAllByPharmacy(Long pharmacyId) {
        return inventoryItemRepository.getAllByPharmacyId(pharmacyId);
    }

    private void checkAdministrator(InventoryItem inventoryItem, User loggedInUser) throws IllegalAccessException {
        Administrator administrator = administratorRepository.getOne(loggedInUser.getId());

        if(!administrator.getPharmacy().getInventory().getInventoryItems().contains(inventoryItem)){
            throw new IllegalAccessException();
        }
    }

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
