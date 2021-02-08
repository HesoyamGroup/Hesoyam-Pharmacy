package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderStatus;
import com.hesoyam.pharmacy.user.dto.UserBasicInfoDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private LocalDateTime deadline;
    private OrderStatus status;
    private UserBasicInfoDTO administrator;
    private List<OrderItemDTO> orderItems;
    private List<OfferDTO> offers;

    public OrderDTO(){
        orderItems = new ArrayList<>();
        offers = new ArrayList<>();
    }

    public OrderDTO(Order order){
        this();
        this.id = order.getId();
        this.deadline = order.getDeadLine();
        this.status = order.getOrderStatus();
        this.administrator = new UserBasicInfoDTO(order.getAdministrator());
        order.getOrderItems().forEach(orderItem -> orderItems.add(new OrderItemDTO(orderItem)));
        order.getOffers().forEach(offer -> offers.add(new OfferDTO(offer)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserBasicInfoDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(UserBasicInfoDTO administrator) {
        this.administrator = administrator;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OfferDTO> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferDTO> offers) {
        this.offers = offers;
    }
}
