package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import java.util.List;

public interface IInventoryService {
    List<Inventory> getAll();
    Inventory findOne(Long id);
}
