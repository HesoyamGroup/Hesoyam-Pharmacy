/***********************************************************************
 * Module:  PharmacyComplaint.java
 * Author:  vule
 * Purpose: Defines the Class PharmacyComplaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.Patient;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PharmacyComplaint extends Complaint {

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "pharmacy_id")
   @NotNull(message = "Pharmacy must be provided.")
   private Pharmacy pharmacy;

   public PharmacyComplaint() {}

   public PharmacyComplaint(String body, Patient patient, ComplaintStatus complaintStatus, Pharmacy pharmacy){
      super(body, patient, complaintStatus);
      this.pharmacy = pharmacy;
   }


   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }
}