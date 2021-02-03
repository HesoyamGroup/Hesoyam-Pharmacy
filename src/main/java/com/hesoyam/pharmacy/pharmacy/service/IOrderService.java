package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    List<ShowOrdersDTO> getActiveForSupplier(Integer page, User user);
    Order get(Long id);
    Order update(Order order);
}
