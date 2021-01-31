package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IInventoryItemService {
    List<InventoryItemPrice> create(InventoryItemPriceDTO itemPriceDTO, User loggedInUser) throws IllegalAccessException;

    List<InventoryItemPrice> update(InventoryItemPriceDTO itemPriceDTO, User user) throws IllegalAccessException;
}
