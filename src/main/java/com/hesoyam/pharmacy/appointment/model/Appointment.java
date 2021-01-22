/***********************************************************************
 * Module:  Appointment.java
 * Author:  WIN 10
 * Purpose: Defines the Class Appointment
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Appointment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length = 500)
   protected String report;
   @Column
   protected AppointmentStatus appointmentStatus;

   @Embedded
   protected DateTimeRange dateTimeRange;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="pharmacy_id", nullable = false)
   protected Pharmacy pharmacy;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="therapy_id")
   protected Therapy therapy;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "patient_id")
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
         }
         if (newPharmacy != null)
         {
            this.pharmacy = newPharmacy;
         }
      }
   }


}