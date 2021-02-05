package com.hesoyam.pharmacy.pharmacy.mapper;

import com.hesoyam.pharmacy.pharmacy.dto.ShowOrderItemDTO;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;

public class OrderItemMapper {

    private OrderItemMapper(){}

    public static ShowOrderItemDTO mapOrderItemToShowOrderItemDTO(OrderItem orderItem){
        return new ShowOrderItemDTO(orderItem.getId(), orderItem.getMedicine().getName(), orderItem.getMedicine().getId(), orderItem.getQuantity());
    }
}
