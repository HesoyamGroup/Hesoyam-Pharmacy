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

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
   @JoinColumn(name = "medicine_specification_id", nullable = false)
   @NotNull(message = "Medicine specification must be set.")
   private MedicineSpecification medicineSpecification;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "manufacturer_id", nullable = false)
   @NotNull(message = "Manufacturer must be provided.")
   private Manufacturer manufacturer;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Prescription mode must be specified.")
   private PrescriptionMode prescriptionMode;

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

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public int getLoyaltyPoints() {
      return loyaltyPoints;
   }

   public void setLoyaltyPoints(int loyaltyPoints) {
      this.loyaltyPoints = loyaltyPoints;
   }

   public MedicineType getMedicineType() {
      return medicineType;
   }

   public void setMedicineType(MedicineType medicineType) {
      this.medicineType = medicineType;
   }

   public MedicineSpecification getMedicineSpecification() {
      return medicineSpecification;
   }

   public void setMedicineSpecification(MedicineSpecification medicineSpecification) {
      this.medicineSpecification = medicineSpecification;
   }

   public Manufacturer getManufacturer() {
      return manufacturer;
   }

   public void setManufacturer(Manufacturer manufacturer) {
      this.manufacturer = manufacturer;
   }

   public PrescriptionMode getPrescriptionMode() {
      return prescriptionMode;
   }

   public void setPrescriptionMode(PrescriptionMode prescriptionMode) {
      this.prescriptionMode = prescriptionMode;
   }
}