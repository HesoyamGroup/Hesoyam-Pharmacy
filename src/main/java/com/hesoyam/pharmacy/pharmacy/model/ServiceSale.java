/***********************************************************************
 * Module:  ServiceSale.java
 * Author:  WIN 10
 * Purpose: Defines the Class ServiceSale
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.appointment.model.Appointment;

import javax.persistence.*;

@Entity
public class ServiceSale extends Sale {

   @Enumerated(EnumType.STRING)
   private ServiceType serviceType;

   @OneToOne(fetch= FetchType.EAGER)
   @JoinColumn(name="appointment_id")
   private Appointment appointment;

   public ServiceType getServiceType() {
      return serviceType;
   }
}