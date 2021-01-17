/***********************************************************************
 * Module:  Shift.java
 * Author:  vule
 * Purpose: Defines the Class Shift
 ***********************************************************************/
package com.hesoyam.pharmacy.employee_management.model;

import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.*;

@Entity
public class Shift {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;

   @Embedded
   private DateTimeRange dateTimeRange;

   @ManyToOne(optional=false)
   @JoinColumn(name="pharmacy_id", referencedColumnName = "id", nullable = false)
   private Pharmacy pharmacy;

}