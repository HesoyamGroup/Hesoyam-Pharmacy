/***********************************************************************
 * Module:  Contraindication.java
 * Author:  WIN 10
 * Purpose: Defines the Class Contraindication
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;

@Entity
public class Contraindication {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 100)
   private String name;
}