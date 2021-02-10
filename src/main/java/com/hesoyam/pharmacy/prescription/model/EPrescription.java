/***********************************************************************
 * Module:  EPrescription.java
 * Author:  Geri
 * Purpose: Defines the Class EPrescription
 ***********************************************************************/
package com.hesoyam.pharmacy.prescription.model;

import com.hesoyam.pharmacy.user.model.Patient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class EPrescription {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   @NotNull(message = "Issuing date must be provided.")
   private LocalDateTime issuingDate;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name="eprescription_id", referencedColumnName="id", nullable = false)
   private List<PrescriptionItem> prescriptionItems;

   @Enumerated(EnumType.STRING)
   @NotNull
   private PrescriptionStatus prescriptionStatus;

   @Version
   @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
   private Long version = 0l;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getIssuingDate() {
      return issuingDate;
   }

   public void setIssuingDate(LocalDateTime issuingDate) {
      this.issuingDate = issuingDate;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public List<PrescriptionItem> getPrescriptionItems() {
      if (prescriptionItems == null)
         prescriptionItems = new ArrayList<>();
      return prescriptionItems;
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public Iterator<PrescriptionItem> getIteratorPrescriptionItem() {
      if (prescriptionItems == null)
         prescriptionItems = new ArrayList<>();
      return prescriptionItems.iterator();
   }

   public void setPrescriptionItems(List<PrescriptionItem> newPrescriptionItem) {
      removeAllPrescriptionItem();
      for (Iterator<PrescriptionItem> iter = newPrescriptionItem.iterator(); iter.hasNext();)
         addPrescriptionItem(iter.next());
   }

   public void addPrescriptionItem(PrescriptionItem newPrescriptionItem) {
      if (newPrescriptionItem == null)
         return;
      if (this.prescriptionItems == null)
         this.prescriptionItems = new ArrayList<>();
      if (!this.prescriptionItems.contains(newPrescriptionItem))
         this.prescriptionItems.add(newPrescriptionItem);
   }

   public void removePrescriptionItem(PrescriptionItem oldPrescriptionItem) {
      if (oldPrescriptionItem == null)
         return;
      if (this.prescriptionItems != null && this.prescriptionItems.contains(oldPrescriptionItem))
         this.prescriptionItems.remove(oldPrescriptionItem);
   }

   public void removeAllPrescriptionItem() {
      if (prescriptionItems != null)
         prescriptionItems.clear();
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
      this.prescriptionStatus = prescriptionStatus;
   }

   public List<Long> getMedicineIds(){
      return prescriptionItems.stream().map( (prescriptionItem -> prescriptionItem.getMedicine().getId())).collect(Collectors.toList());
   }
}