/***********************************************************************
 * Module:  LoyaltyAccount.java
 * Author:  WIN 10
 * Purpose: Defines the Class LoyaltyAccount
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class LoyaltyAccount {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(0)
   private int points;

   @OneToOne(optional=false, fetch = FetchType.EAGER)
   @JoinColumn(name="patient_id", nullable = false)
   private Patient patient;

   @ManyToOne(optional = true)
   @JoinColumn(name="membership_id", nullable = true)
   private LoyaltyAccountMembership loyaltyAccountMembership;

   public LoyaltyAccount() {
   }

   public LoyaltyAccount(int points, Patient patient, LoyaltyAccountMembership loyaltyAccountMembership) {
      this.points = points;
      this.patient = patient;
      this.loyaltyAccountMembership = loyaltyAccountMembership;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getPoints() {
      return points;
   }

   public void setPoints(int points) {
      this.points = points;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public LoyaltyAccountMembership getLoyaltyAccountMembership() {
      return loyaltyAccountMembership;
   }

   public void setLoyaltyAccountMembership(LoyaltyAccountMembership loyaltyAccountMembership) {
      this.loyaltyAccountMembership = loyaltyAccountMembership;
   }

   public double getDiscountedPrice(double price){
      if(loyaltyAccountMembership == null) return price;

      double discount = loyaltyAccountMembership.getDiscount();
      return price - calculatePercentage(price, discount);
   }

   private double calculatePercentage(double value, double percentage){
      return (value * percentage)/100;
   }
}