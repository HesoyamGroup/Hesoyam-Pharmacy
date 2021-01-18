/***********************************************************************
 * Module:  Dosage.java
 * Author:  Geri
 * Purpose: Defines the Class Dosage
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Dosage{

   @Column
   private Double quantity;

   @Enumerated(EnumType.STRING)
   private CompositionUnit unit;
}