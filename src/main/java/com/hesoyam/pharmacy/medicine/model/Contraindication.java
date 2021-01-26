/***********************************************************************
 * Module:  Contraindication.java
 * Author:  WIN 10
 * Purpose: Defines the Class Contraindication
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contraindication {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 100)
   @NotNull(message="Contraindication name must be provided.")
   @Length(min=3, max=100, message = "Contraindication name should be between 3 and 100 characters")
   private String name;

   public Contraindication() {
   }

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
}