/***********************************************************************
 * Module:  CompositionItem.java
 * Author:  Geri
 * Purpose: Defines the Class CompositionItem
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;

@Entity
public class CompositionItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 40)
   private String name;

   @Embedded
   private Dosage quantity;
}