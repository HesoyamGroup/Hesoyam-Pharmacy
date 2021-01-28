/***********************************************************************
 * Module:  CompositionItem.java
 * Author:  Geri
 * Purpose: Defines the Class CompositionItem
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CompositionItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 40)
   @NotNull(message = "Composition item name must be provided.")
   @Length(min=3, max=40, message = "Medicine name length should be between 3 and 40 characters.")
   private String name;

   @Embedded
   private Dosage quantity;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Dosage getQuantity() {
      return quantity;
   }

   public void setQuantity(Dosage quantity) {
      this.quantity = quantity;
   }
}