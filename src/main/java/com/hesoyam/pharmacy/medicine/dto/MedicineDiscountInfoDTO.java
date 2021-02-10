package com.hesoyam.pharmacy.medicine.dto;

public class MedicineDiscountInfoDTO {
    private double currentPrice;
    private double discountedPrice;
    private boolean hasDiscount;

    public MedicineDiscountInfoDTO() {
    }

    public MedicineDiscountInfoDTO(double currentPrice, double discountedPrice) {
        this.currentPrice = currentPrice;
        this.discountedPrice = discountedPrice;
        this.hasDiscount = false;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }
}
