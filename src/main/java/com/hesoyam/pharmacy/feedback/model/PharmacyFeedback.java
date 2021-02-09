/***********************************************************************
 * Module:  PharmacyFeedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class PharmacyFeedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PharmacyFeedback extends Feedback {

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "pharmacy_id")
   private Pharmacy pharmacy;

   public void update(PharmacyFeedback pharmacyFeedback){
      this.pharmacy = pharmacyFeedback.getPharmacy();
      this.comment = pharmacyFeedback.getComment();
      this.patient = pharmacyFeedback.getPatient();
      this.rating = pharmacyFeedback.getRating();
   }

   public PharmacyFeedback() {
   }

   public PharmacyFeedback(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }
}