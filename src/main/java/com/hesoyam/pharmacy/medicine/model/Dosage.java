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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Dosage{

   @Column
   @NotNull(message = "Dosage quantity must be provided.")
   @Min(value = 0, message = "Dosage quantity must be a non-negative integer.")
   private Double quantity;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Composition unit must be specified.")
   private CompositionUnit unit;

   public Double getQuantity() {
      return quantity;
   }

   public void setQuantity(Double quantity) {
      this.quantity = quantity;
   }

   public CompositionUnit getUnit() {
      return unit;
   }

   public void setUnit(CompositionUnit unit) {
      this.unit = unit;
   }
}