/***********************************************************************
 * Module:  MedicineReservation.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservation
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class MedicineReservation {
   private Long id;
   private Date pickUpDate;
   private String key;
   
   public java.util.Collection<MedicineReservationItem> medicineReservationItem;
   public Patient patient;
   public MedicineReservationStatus medicineReservationStatus;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<MedicineReservationItem> getMedicineReservationItem() {
      if (medicineReservationItem == null)
         medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      return medicineReservationItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicineReservationItem() {
      if (medicineReservationItem == null)
         medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      return medicineReservationItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicineReservationItem */
   public void setMedicineReservationItem(java.util.Collection<MedicineReservationItem> newMedicineReservationItem) {
      removeAllMedicineReservationItem();
      for (java.util.Iterator iter = newMedicineReservationItem.iterator(); iter.hasNext();)
         addMedicineReservationItem((MedicineReservationItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicineReservationItem */
   public void addMedicineReservationItem(MedicineReservationItem newMedicineReservationItem) {
      if (newMedicineReservationItem == null)
         return;
      if (this.medicineReservationItem == null)
         this.medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      if (!this.medicineReservationItem.contains(newMedicineReservationItem))
         this.medicineReservationItem.add(newMedicineReservationItem);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicineReservationItem */
   public void removeMedicineReservationItem(MedicineReservationItem oldMedicineReservationItem) {
      if (oldMedicineReservationItem == null)
         return;
      if (this.medicineReservationItem != null)
         if (this.medicineReservationItem.contains(oldMedicineReservationItem))
            this.medicineReservationItem.remove(oldMedicineReservationItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicineReservationItem() {
      if (medicineReservationItem != null)
         medicineReservationItem.clear();
   }

}