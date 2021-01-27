package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.repository.OrderItemRepository;
import com.hesoyam.pharmacy.pharmacy.repository.OrderRepository;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
