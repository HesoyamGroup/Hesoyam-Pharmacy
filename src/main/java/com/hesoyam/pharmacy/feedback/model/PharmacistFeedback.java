/***********************************************************************
 * Module:  PharmacistFeedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class PharmacistFeedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Pharmacist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PharmacistFeedback extends Feedback {

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "pharmacist_id", nullable = false)
   private Pharmacist pharmacist;

}