/***********************************************************************
 * Module:  Counseling.java
 * Author:  vule
 * Purpose: Defines the Class Counseling
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.user.model.Pharmacist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Counseling extends Appointment {

   @ManyToOne(fetch = FetchType.LAZY, optional=true)
   @JoinColumn(name="pharmacist_id")
   private Pharmacist pharmacist;
}