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

@Entity
public class PharmacyComplaint extends Complaint {

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "pharmacy_id", nullable = false)
   private Pharmacy pharmacy;
}