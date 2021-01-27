/***********************************************************************
 * Module:  PharmacyComplaint.java
 * Author:  vule
 * Purpose: Defines the Class PharmacyComplaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

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
}