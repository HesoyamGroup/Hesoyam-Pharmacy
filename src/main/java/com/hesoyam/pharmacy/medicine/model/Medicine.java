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

   @Enumerated(EnumType.STRING)
   private MedicineType medicineType;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "medicine_specification_id")
   private MedicineSpecification medicineSpecification;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "manufacturer_id", nullable = false)
   private Manufacturer manufacturer;

   @Enumerated(EnumType.STRING)
   private PrescriptionMode prescriptionMode;
}