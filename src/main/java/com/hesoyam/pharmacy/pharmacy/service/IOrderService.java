package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.dto.OrderDTO;
import com.hesoyam.pharmacy.pharmacy.dto.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    List<ShowOrdersDTO> getActiveForSupplier(Integer page, User user);
    Order get(Long id);
    Order update(Order order);
    Order updateDeadline(User user, OrderDTO orderDTO) throws IllegalAccessException;
    ShowOrdersDTO getBasicOrderInfo(Long id);

    Order create(ShowOrdersDTO order, Administrator administrator);

    List<Order> getAllByAdministratorPharmacy(User user);

    void delete(User user, Long id) throws IllegalAccessException;
}
