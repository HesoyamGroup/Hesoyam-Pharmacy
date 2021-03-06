/***********************************************************************
 * Module:  PrescriptionItem.java
 * Author:  Geri
 * Purpose: Defines the Class PrescriptionItem
 ***********************************************************************/
package com.hesoyam.pharmacy.prescription.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class PrescriptionItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(value = 0, message = "Quantity must be non-negative integer.")
   private int quantity;

   @ManyToOne(fetch = FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id")
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