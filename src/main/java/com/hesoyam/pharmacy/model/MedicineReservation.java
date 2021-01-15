/***********************************************************************
 * Module:  MedicineReservation.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservation
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;

public class MedicineReservation {
   private Long id;
   private LocalDateTime pickUpDate;
   private String key;
   
   private java.util.Collection<MedicineReservationItem> medicineReservationItem;
   private Patient patient;
   private MedicineReservationStatus medicineReservationStatus;

   public java.util.Collection<MedicineReservationItem> getMedicineReservationItem() {
      if (medicineReservationItem == null)
         medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      return medicineReservationItem;
   }

   public java.util.Iterator getIteratorMedicineReservationItem() {
      if (medicineReservationItem == null)
         medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      return medicineReservationItem.iterator();
   }

   public void setMedicineReservationItem(java.util.Collection<MedicineReservationItem> newMedicineReservationItem) {
      removeAllMedicineReservationItem();
      for (java.util.Iterator iter = newMedicineReservationItem.iterator(); iter.hasNext();)
         addMedicineReservationItem((MedicineReservationItem)iter.next());
   }

   public void addMedicineReservationItem(MedicineReservationItem newMedicineReservationItem) {
      if (newMedicineReservationItem == null)
         return;
      if (this.medicineReservationItem == null)
         this.medicineReservationItem = new java.util.HashSet<MedicineReservationItem>();
      if (!this.medicineReservationItem.contains(newMedicineReservationItem))
         this.medicineReservationItem.add(newMedicineReservationItem);
   }

   public void removeMedicineReservationItem(MedicineReservationItem oldMedicineReservationItem) {
      if (oldMedicineReservationItem == null)
         return;
      if (this.medicineReservationItem != null)
         if (this.medicineReservationItem.contains(oldMedicineReservationItem))
            this.medicineReservationItem.remove(oldMedicineReservationItem);
   }

   public void removeAllMedicineReservationItem() {
      if (medicineReservationItem != null)
         medicineReservationItem.clear();
   }

}