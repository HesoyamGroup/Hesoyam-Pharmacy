package com.hesoyam.pharmacy.pharmacy.DTO;

import com.hesoyam.pharmacy.pharmacy.model.OfferStatus;

import java.time.LocalDateTime;

public class OfferDTO {
    private Long id;
    private double totalPrice;
    private LocalDateTime deliveryDate;
    private OfferStatus offerStatus;
    public OfferDTO(){}

    public OfferDTO(Long id, double totalPrice, LocalDateTime deliveryDate, OfferStatus offerStatus) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.deliveryDate = deliveryDate;
        this.offerStatus = offerStatus;
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
}
