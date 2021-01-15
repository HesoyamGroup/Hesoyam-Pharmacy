/***********************************************************************
 * Module:  Appointment.java
 * Author:  WIN 10
 * Purpose: Defines the Class Appointment
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public abstract class Appointment {
   private Long id;
   private String report;
   
   private AppointmentStatus appointmentStatus;
   private DateTimeRange dateTimeRange;
   private Pharmacy pharmacy;
   
   
   /** @pdGenerated default parent getter */
   public Pharmacy getPharmacy() {
      return pharmacy;
   }
   
   /** @pdGenerated default parent setter
     * @param newPharmacy */
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
   
   public Therapy therapy;
   public Patient patient;

}