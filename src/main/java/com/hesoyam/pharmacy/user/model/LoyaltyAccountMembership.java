/***********************************************************************
 * Module:  LoyaltyAccountMembership.java
 * Author:  WIN 10
 * Purpose: Defines the Class LoyaltyAccountMembership
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class LoyaltyAccountMembership {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true, nullable = false)
   @Length(min=2, max=75, message = "Loyalty account membership name must have at least 2 characters.(75max)")
   private String name;

   @Column
   @Min(0)
   @Max(100)
   private double discount;

   @Column
   @Min(0)
   private int minPoints;

   @ManyToOne(optional = true, fetch = FetchType.EAGER)
   @JoinColumn(name="config_id", nullable = false)
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