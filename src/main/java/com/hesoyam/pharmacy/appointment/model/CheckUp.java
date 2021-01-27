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
import javax.validation.constraints.NotNull;

@Entity
public class CheckUp extends Appointment {
   @ManyToOne(fetch = FetchType.LAZY, optional=true)
   @JoinColumn(name="dermatologist_id")
   @NotNull(message = "Dermatologist must be set.")
   private Dermatologist dermatologist;


   public Dermatologist getDermatologist() {
      return dermatologist;
   }

   public void setDermatologist(Dermatologist dermatologist) {
      this.dermatologist = dermatologist;
   }
}