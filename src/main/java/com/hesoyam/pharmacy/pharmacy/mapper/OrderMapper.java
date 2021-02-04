package com.hesoyam.pharmacy.pharmacy.mapper;

import com.hesoyam.pharmacy.pharmacy.dto.ShowOrderItemDTO;
import com.hesoyam.pharmacy.pharmacy.dto.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
    }


    public static ShowOrdersDTO mapOrderToShowOrderDTO(Order order) {
        return new ShowOrdersDTO(order.getId(), order.getDeadLine(), order.getPharmacy().getName(), mapOrderItemsToDTO(order.getOrderItems()));
    }

    private static List<ShowOrderItemDTO> mapOrderItemsToDTO(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> OrderItemMapper.mapOrderItemToShowOrderItemDTO(orderItem)).collect(Collectors.toList());
    }
}
