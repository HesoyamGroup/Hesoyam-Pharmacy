package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientLoyaltyProgramDTO {

    Integer points;
    String categoryName;
    Integer minPoint;
    Double discount;
    int penaltyPoints;


    public PatientLoyaltyProgramDTO() {
    }

    public PatientLoyaltyProgramDTO(LoyaltyAccount loyaltyAccount){
        this.points = loyaltyAccount.getPoints();
        this.categoryName = loyaltyAccount.getLoyaltyAccountMembership().getName();
        this.minPoint = loyaltyAccount.getLoyaltyAccountMembership().getMinPoints();
        this.discount = loyaltyAccount.getLoyaltyAccountMembership().getDiscount();
        this.penaltyPoints = 0;
    }

    public PatientLoyaltyProgramDTO(LoyaltyAccount loyaltyAccount, int penaltyPoints){
        this.points = loyaltyAccount.getPoints();
        this.categoryName = loyaltyAccount.getLoyaltyAccountMembership().getName();
        this.minPoint = loyaltyAccount.getLoyaltyAccountMembership().getMinPoints();
        this.discount = loyaltyAccount.getLoyaltyAccountMembership().getDiscount();
        this.penaltyPoints = penaltyPoints;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(Integer minPoint) {
        this.minPoint = minPoint;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }
}
