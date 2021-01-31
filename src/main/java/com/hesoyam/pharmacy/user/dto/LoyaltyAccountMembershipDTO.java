package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoyaltyAccountMembershipDTO {
    private Long id;
    @NotNull(message = "Loyalty accoutn membership name must be provided.")
    @Length(min = 2, max=75, message = "Loyalty account membership name must have at least 2 characters.(75max)")
    private String name;

    @Min(0)
    @Max(100)
    private double discount;
    @Min(0)
    private int minPoints;

    @NotNull(message = "Loyalty program config must be specified.")
    private LoyaltyProgramConfig loyaltyProgramConfig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public LoyaltyProgramConfig getLoyaltyProgramConfig() {
        return loyaltyProgramConfig;
    }

    public void setLoyaltyProgramConfig(LoyaltyProgramConfig loyaltyProgramConfig) {
        this.loyaltyProgramConfig = loyaltyProgramConfig;
    }
}
