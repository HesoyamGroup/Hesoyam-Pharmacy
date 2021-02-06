/***********************************************************************
 * Module:  MedicineSale.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineSale
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class MedicineSale extends Sale {
   @ManyToOne(fetch = FetchType.EAGER) // Note [Gergo]: optional=false will not let ServiceSale records to be inserted in super table Sales
   @JoinColumn(name="medicine_id")
   private Medicine medicine;
   public MedicineSale(){

   }

   public MedicineSale(LocalDateTime dateOfSale, double price, Pharmacy pharmacy, Medicine medicine) {
      super(dateOfSale, price, pharmacy);
      this.medicine = medicine;
   }

   public Medicine getMedicine() {
      return medicine;
   }

   public void setMedicine(Medicine medicine) {
      this.medicine = medicine;
   }
}