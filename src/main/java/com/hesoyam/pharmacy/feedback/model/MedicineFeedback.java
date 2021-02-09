/***********************************************************************
 * Module:  MedicineFeedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineFeedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicineFeedback extends Feedback {

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "medicine_id")
   private Medicine medicine;

   public void update(MedicineFeedback medicineFeedback){
      this.medicine = medicineFeedback.getMedicine();
      this.comment = medicineFeedback.getComment();
      this.patient = medicineFeedback.getPatient();
      this.rating = medicineFeedback.getRating();
   }

   public MedicineFeedback(Medicine medicine) {
      this.medicine = medicine;
   }

   public MedicineFeedback() {
   }

   public Medicine getMedicine() {
      return medicine;
   }

   public void setMedicine(Medicine medicine) {
      this.medicine = medicine;
   }
}