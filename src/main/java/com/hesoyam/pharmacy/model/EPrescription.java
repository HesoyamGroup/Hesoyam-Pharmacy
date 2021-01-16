/***********************************************************************
 * Module:  EPrescription.java
 * Author:  Geri
 * Purpose: Defines the Class EPrescription
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EPrescription {
   private Long prescriptionId;
   private LocalDateTime issuingDate;
   
   private Patient patient;
   private List<PrescriptionItem> prescriptionItems;

   public List<PrescriptionItem> getPrescriptionItems() {
      if (prescriptionItems == null)
         prescriptionItems = new ArrayList<PrescriptionItem>();
      return prescriptionItems;
   }

   public Iterator getIteratorPrescriptionItem() {
      if (prescriptionItems == null)
         prescriptionItems = new ArrayList<PrescriptionItem>();
      return prescriptionItems.iterator();
   }

   public void setPrescriptionItems(List<PrescriptionItem> newPrescriptionItem) {
      removeAllPrescriptionItem();
      for (Iterator iter = newPrescriptionItem.iterator(); iter.hasNext();)
         addPrescriptionItem((PrescriptionItem)iter.next());
   }

   public void addPrescriptionItem(PrescriptionItem newPrescriptionItem) {
      if (newPrescriptionItem == null)
         return;
      if (this.prescriptionItems == null)
         this.prescriptionItems = new ArrayList<PrescriptionItem>();
      if (!this.prescriptionItems.contains(newPrescriptionItem))
         this.prescriptionItems.add(newPrescriptionItem);
   }

   public void removePrescriptionItem(PrescriptionItem oldPrescriptionItem) {
      if (oldPrescriptionItem == null)
         return;
      if (this.prescriptionItems != null)
         if (this.prescriptionItems.contains(oldPrescriptionItem))
            this.prescriptionItems.remove(oldPrescriptionItem);
   }

   public void removeAllPrescriptionItem() {
      if (prescriptionItems != null)
         prescriptionItems.clear();
   }

}