/***********************************************************************
 * Module:  EPrescription.java
 * Author:  Geri
 * Purpose: Defines the Class EPrescription
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class EPrescription {
   private Long prescriptionId;
   private Date issuingDate;
   
   public Patient patient;
   public java.util.List<PrescriptionItem> prescriptionItem;
   
   
   /** @pdGenerated default getter */
   public java.util.List<PrescriptionItem> getPrescriptionItem() {
      if (prescriptionItem == null)
         prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      return prescriptionItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPrescriptionItem() {
      if (prescriptionItem == null)
         prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      return prescriptionItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPrescriptionItem */
   public void setPrescriptionItem(java.util.List<PrescriptionItem> newPrescriptionItem) {
      removeAllPrescriptionItem();
      for (java.util.Iterator iter = newPrescriptionItem.iterator(); iter.hasNext();)
         addPrescriptionItem((PrescriptionItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPrescriptionItem */
   public void addPrescriptionItem(PrescriptionItem newPrescriptionItem) {
      if (newPrescriptionItem == null)
         return;
      if (this.prescriptionItem == null)
         this.prescriptionItem = new java.util.ArrayList<PrescriptionItem>();
      if (!this.prescriptionItem.contains(newPrescriptionItem))
         this.prescriptionItem.add(newPrescriptionItem);
   }
   
   /** @pdGenerated default remove
     * @param oldPrescriptionItem */
   public void removePrescriptionItem(PrescriptionItem oldPrescriptionItem) {
      if (oldPrescriptionItem == null)
         return;
      if (this.prescriptionItem != null)
         if (this.prescriptionItem.contains(oldPrescriptionItem))
            this.prescriptionItem.remove(oldPrescriptionItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPrescriptionItem() {
      if (prescriptionItem != null)
         prescriptionItem.clear();
   }

}