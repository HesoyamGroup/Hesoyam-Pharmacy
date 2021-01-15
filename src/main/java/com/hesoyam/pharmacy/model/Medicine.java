/***********************************************************************
 * Module:  Medicine.java
 * Author:  WIN 10
 * Purpose: Defines the Class Medicine
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Medicine {
   private Long medicineId;
   private String name;
   private String notes;
   private int loyaltyPoints;
   
   private MedicineType medicineType;
   private MedicineSpecification medicineSpecification;
   
   private Manufacturer manufacturer;
   private PrescriptionMode prescriptionMode;
}