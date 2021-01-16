/***********************************************************************
 * Module:  Manufacturer.java
 * Author:  Geri
 * Purpose: Defines the Class Manufacturer
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import com.hesoyam.pharmacy.location.model.Address;

import javax.persistence.*;

@Entity
public class Manufacturer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(length=50)
   private String name;
   private Address address;
}