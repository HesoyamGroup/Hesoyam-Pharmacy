/***********************************************************************
 * Module:  EPrescription.java
 * Author:  Geri
 * Purpose: Defines the Class EPrescription
 ***********************************************************************/
package com.hesoyam.pharmacy.prescription.model;

import com.hesoyam.pharmacy.user.model.Patient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class EPrescription {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private LocalDateTime issuingDate;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name="eprescription_id", referencedColumnName="id", nullable = false)
   private List<PrescriptionItem> prescriptionItems;

   public List<PrescriptionItem> getPrescriptionItems() {
      if (prescriptionItems == null)
         prescriptionItems = new ArrayList<>();
      return prescriptionItems;
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

}