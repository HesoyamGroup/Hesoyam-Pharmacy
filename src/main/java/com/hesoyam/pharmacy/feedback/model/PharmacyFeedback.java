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
}