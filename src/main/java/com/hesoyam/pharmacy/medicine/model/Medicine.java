/***********************************************************************
 * Module:  Medicine.java
 * Author:  WIN 10
 * Purpose: Defines the Class Medicine
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;

@Entity
public class Medicine {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 75)
   private String name;
   @Column(length=200)
   private String notes;
   @Column
   private int loyaltyPoints;

   @Column
   private MedicineType medicineType;

   private MedicineSpecification medicineSpecification;
   
   private Manufacturer manufacturer;
   private PrescriptionMode prescriptionMode;
}