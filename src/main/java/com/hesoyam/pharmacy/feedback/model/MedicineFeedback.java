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

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "medicine_id", nullable = false)
   private Medicine medicine;
}