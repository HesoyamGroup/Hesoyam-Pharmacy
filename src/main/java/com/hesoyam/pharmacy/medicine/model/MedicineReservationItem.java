/***********************************************************************
 * Module:  MedicineReservationItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservationItem
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class MedicineReservationItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(0)
   private int quantity;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "medicine_id", nullable = false, referencedColumnName = "id")
   private Medicine medicine;

   public MedicineReservationItem(Long id, @Min(0) int quantity, Medicine medicine) {
      this.id = id;
      this.quantity = quantity;
      this.medicine = medicine;
   }

   public MedicineReservationItem(@Min(0) int quantity, Medicine medicine) {
      this.quantity = quantity;
      this.medicine = medicine;
   }

   public MedicineReservationItem() {
   }

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