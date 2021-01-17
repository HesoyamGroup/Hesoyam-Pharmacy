/***********************************************************************
 * Module:  DermatologistFeedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class DermatologistFeedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Dermatologist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DermatologistFeedback extends Feedback {

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "dermatologist_id", nullable = false)
   private Dermatologist dermatologist;

}