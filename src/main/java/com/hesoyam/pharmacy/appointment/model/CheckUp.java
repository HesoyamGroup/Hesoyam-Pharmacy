/***********************************************************************
 * Module:  CheckUp.java
 * Author:  WIN 10
 * Purpose: Defines the Class CheckUp
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.user.model.Dermatologist;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("CHECKUP")
public class CheckUp extends Appointment {
   @ManyToOne(fetch = FetchType.LAZY, optional=true)
   @JoinColumn(name="dermatologist_id")
   @NotNull(message = "Dermatologist must be set.")
   private Dermatologist dermatologist;

   public CheckUp(){
      //Empty ctor for JSON serializer
   }


   public Dermatologist getDermatologist() {
      return dermatologist;
   }

   public void setDermatologist(Dermatologist dermatologist) {
      this.dermatologist = dermatologist;
   }

   public void update(CheckUp checkup){
      this.id = checkup.getId();
      this.dermatologist = checkup.getDermatologist();
      this.dateTimeRange = checkup.getDateTimeRange();
      this.pharmacy = checkup.getPharmacy();
      this.appointmentStatus = checkup.getAppointmentStatus();
      this.patient = checkup.getPatient();
      this.price = checkup.getPrice();
      this.report = checkup.getReport();
      this.therapy = checkup.getTherapy();
   }
}