/***********************************************************************
 * Module:  EPrescription.java
 * Author:  Geri
 * Purpose: Defines the Class EPrescription
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;

public class EPrescription {
   private Long prescriptionId;
   private LocalDateTime issuingDate;
   
   private Patient patient;
   private java.util.List<PrescriptionItem> prescriptionItem;

   public java.util.List<PrescriptionItem> getPrescriptionItem() {
      if (prescriptionItem == null)
         prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      return prescriptionItem;
   }

   public java.util.Iterator getIteratorPrescriptionItem() {
      if (prescriptionItem == null)
         prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      return prescriptionItem.iterator();
   }

   public void setPrescriptionItem(java.util.List<PrescriptionItem> newPrescriptionItem) {
      removeAllPrescriptionItem();
      for (java.util.Iterator iter = newPrescriptionItem.iterator(); iter.hasNext();)
         addPrescriptionItem((PrescriptionItem)iter.next());
   }

   public void addPrescriptionItem(PrescriptionItem newPrescriptionItem) {
      if (newPrescriptionItem == null)
         return;
      if (this.prescriptionItem == null)
         this.prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      if (!this.prescriptionItem.contains(newPrescriptionItem))
         this.prescriptionItem.add(newPrescriptionItem);
   }

   public void removePrescriptionItem(PrescriptionItem oldPrescriptionItem) {
      if (oldPrescriptionItem == null)
         return;
      if (this.prescriptionItem != null)
         if (this.prescriptionItem.contains(oldPrescriptionItem))
            this.prescriptionItem.remove(oldPrescriptionItem);
   }

   public void removeAllPrescriptionItem() {
      if (prescriptionItem != null)
         prescriptionItem.clear();
   }

}