/***********************************************************************
 * Module:  Medicine.java
 * Author:  WIN 10
 * Purpose: Defines the Class Medicine
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Medicine {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 75)
   @NotNull(message = "You must provide medicine name")
   @Length(min=3, max=75, message = "Medicine name length should be between 3 and 75 characters.")
   private String name;

   @Column(length=200)
   @NotNull(message = "Notes must be provided, even if empty.")
   @Length(max=200, message = "Medicine notes should not exceed 200 characters.")
   private String notes;

   @Column
   @Min(value = 0, message = "Loyalty points you receive from buying this medicine must be a non-negative integer.")
   private int loyaltyPoints;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Medicine type must be provided.")
   private MedicineType medicineType;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "medicine_specification_id")
   private MedicineSpecification medicineSpecification;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "manufacturer_id", nullable = false)
   private Manufacturer manufacturer;

   @Enumerated(EnumType.STRING)
   private PrescriptionMode prescriptionMode;
}