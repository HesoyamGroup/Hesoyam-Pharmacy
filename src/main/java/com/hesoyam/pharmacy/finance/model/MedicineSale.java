/***********************************************************************
 * Module:  MedicineSale.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineSale
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicineSale extends Sale {
   @ManyToOne(fetch = FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id", nullable = false)
   private Medicine medicine;
}