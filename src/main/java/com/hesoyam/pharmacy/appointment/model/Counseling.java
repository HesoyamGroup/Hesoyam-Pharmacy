/***********************************************************************
 * Module:  Counseling.java
 * Author:  vule
 * Purpose: Defines the Class Counseling
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.user.model.Pharmacist;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("COUNSELING")
public class Counseling extends Appointment {

   @ManyToOne(fetch = FetchType.LAZY, optional=true)
   @JoinColumn(name="pharmacist_id")
   @NotNull(message = "Pharmacist must be set.")
   private Pharmacist pharmacist;


   public Pharmacist getPharmacist() {
      return pharmacist;
   }

   public void setPharmacist(Pharmacist pharmacist) {
      this.pharmacist = pharmacist;
   }

   public void update(Counseling counseling){
      this.id = counseling.getId();
      this.pharmacist = counseling.getPharmacist();
      this.dateTimeRange = counseling.getDateTimeRange();
      this.pharmacy = counseling.getPharmacy();
      this.appointmentStatus = counseling.getAppointmentStatus();
      this.patient = counseling.getPatient();
      this.price = counseling.getPrice();
      this.report = counseling.getReport();
      this.therapy = counseling.getTherapy();
   }
}