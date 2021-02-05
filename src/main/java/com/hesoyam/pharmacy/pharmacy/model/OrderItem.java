/***********************************************************************
 * Module:  OrderItem.java
 * Author:  vule
 * Purpose: Defines the Class OrderItem
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class OrderItem {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;
   @Column
   @Min(0)
   private int quantity;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="medicine_id", nullable=false)
   private Medicine medicine;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public Medicine getMedicine() {
      return medicine;
   }

   public void setMedicine(Medicine medicine) {
      this.medicine = medicine;
   }
}