/***********************************************************************
 * Module:  ServiceSale.java
 * Author:  WIN 10
 * Purpose: Defines the Class ServiceSale
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import com.hesoyam.pharmacy.appointment.model.Appointment;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ServiceSale extends Sale {
   @OneToOne(fetch= FetchType.EAGER, optional = false)
   @JoinColumn(name="appointment_id", nullable = false)
   private Appointment appointment;
}