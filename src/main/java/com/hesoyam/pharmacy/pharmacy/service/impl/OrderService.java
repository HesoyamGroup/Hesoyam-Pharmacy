package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.DTO.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.mapper.OrderMapper;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderStatus;
import com.hesoyam.pharmacy.pharmacy.repository.OrderItemRepository;
import com.hesoyam.pharmacy.pharmacy.repository.OrderRepository;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ShowOrdersDTO> getActiveForSupplier(Integer page, User user) {
        List<Order> activeOrders = orderRepository.getUserPendingActionOrders(user.getId());
        return activeOrders.stream().map((order) -> OrderMapper.mapOrderToShowOrderDTO(order)).collect(Collectors.toList());
    }
}
