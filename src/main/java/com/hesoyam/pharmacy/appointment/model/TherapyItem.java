/***********************************************************************
 * Module:  TherapyItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class TherapyItem
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class TherapyItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(0)
   private int quantity;

   @Column
   @Min(0)
   private int numberOfDays;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="medicine_id", nullable = false)
   @NotNull(message = "Therapy item medicine must be specified.")
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
      if(quantity > 0)
         this.quantity = quantity;
   }

   public int getNumberOfDays() {
      return numberOfDays;
   }

   public void setNumberOfDays(int numberOfDays) {
      if(numberOfDays > 0)
         this.numberOfDays = numberOfDays;
   }

   public Medicine getMedicine() {
      return medicine;
   }

   public void setMedicine(Medicine medicine) {
      this.medicine = medicine;
   }

   public void setFromMedicineReservation(MedicineReservationItem medicineReservationItem){
      this.medicine = medicineReservationItem.getMedicine();
      this.quantity = medicineReservationItem.getQuantity();
   }
}