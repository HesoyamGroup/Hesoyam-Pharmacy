/***********************************************************************
 * Module:  Medicine.java
 * Author:  WIN 10
 * Purpose: Defines the Class Medicine
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Medicine {
   private Long medicineId;
   private String name;
   private String notes;
   private int loyaltyPoints;
   
   private MedicineType medicineType;
   private MedicineSpecification medicineSpecification;
   
   public Manufacturer manufacturer;
   public PrescriptionMode prescriptionMode;

}