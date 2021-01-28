package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import com.hesoyam.pharmacy.pharmacy.repository.InventoryRepository;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class InventoryService implements IInventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAll() { return inventoryRepository.findAll(); }

    @Override
    public Inventory findOne(Long id) {
        return inventoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
