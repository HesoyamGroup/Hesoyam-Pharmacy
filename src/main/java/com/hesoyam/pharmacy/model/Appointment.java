/***********************************************************************
 * Module:  Appointment.java
 * Author:  WIN 10
 * Purpose: Defines the Class Appointment
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public abstract class Appointment {
   protected Long id;
   protected String report;
   protected AppointmentStatus appointmentStatus;
   protected DateTimeRange dateTimeRange;
   protected Pharmacy pharmacy;
   protected Therapy therapy;
   protected Patient patient;

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy newPharmacy) {
      if (this.pharmacy == null || !this.pharmacy.equals(newPharmacy))
      {
         if (this.pharmacy != null)
         {
            Pharmacy oldPharmacy = this.pharmacy;
            this.pharmacy = null;
            oldPharmacy.removeAppointment(this);
         }
         if (newPharmacy != null)
         {
            this.pharmacy = newPharmacy;
            this.pharmacy.addAppointment(this);
         }
      }
   }


}