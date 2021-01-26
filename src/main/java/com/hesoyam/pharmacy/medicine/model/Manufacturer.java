/***********************************************************************
 * Module:  Manufacturer.java
 * Author:  Geri
 * Purpose: Defines the Class Manufacturer
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import com.hesoyam.pharmacy.location.model.Address;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Manufacturer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length=50)
   @NotNull
   @Length(min=3, max=50, message = "Manufacter name length should be between 3 and 50 characters.")
   private String name;

   @Embedded
   private Address address;
}