/***********************************************************************
 * Module:  CheckUp.java
 * Author:  WIN 10
 * Purpose: Defines the Class CheckUp
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.user.model.Dermatologist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CheckUp extends Appointment {
   @ManyToOne(fetch = FetchType.LAZY, optional=true)
   @JoinColumn(name="dermatologist_id")
   private Dermatologist dermatologist;
}