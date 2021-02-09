package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.model.OfferStatus;
import com.hesoyam.pharmacy.user.dto.UserBasicInfoDTO;

import java.time.LocalDateTime;

public class OfferDTO {
    private Long id;
    private double totalPrice;
    private LocalDateTime deliveryDate;
    private OfferStatus offerStatus;
    private Long orderId;
    private UserBasicInfoDTO supplier;

    public OfferDTO(){
        //Empty ctor for JSON serializer
    }

    public OfferDTO(Long id, double totalPrice, LocalDateTime deliveryDate, OfferStatus offerStatus, Long orderId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.deliveryDate = deliveryDate;
        this.offerStatus = offerStatus;
        this.orderId = orderId;
    }

    public OfferDTO(Offer offer){
        this.id = offer.getId();
        this.totalPrice = offer.getTotalPrice();
        this.deliveryDate = offer.getDeliveryDate();
        this.offerStatus = offer.getOfferStatus();
        this.supplier = new UserBasicInfoDTO(offer.getSupplier());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UserBasicInfoDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(UserBasicInfoDTO supplier) {
        this.supplier = supplier;
    }
}
