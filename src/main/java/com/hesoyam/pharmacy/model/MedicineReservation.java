/***********************************************************************
 * Module:  MedicineReservation.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservation
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MedicineReservation {
   private Long id;
   private LocalDateTime pickUpDate;
   private String key;
   
   private List<MedicineReservationItem> medicineReservationItems;
   private Patient patient;
   private MedicineReservationStatus medicineReservationStatus;

   public List<MedicineReservationItem> getMedicineReservationItems() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<MedicineReservationItem>();
      return medicineReservationItems;
   }

   public Iterator getIteratorMedicineReservationItem() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<MedicineReservationItem>();
      return medicineReservationItems.iterator();
   }

   public void setMedicineReservationItems(List<MedicineReservationItem> newMedicineReservationItem) {
      removeAllMedicineReservationItem();
      for (Iterator iter = newMedicineReservationItem.iterator(); iter.hasNext();)
         addMedicineReservationItem((MedicineReservationItem)iter.next());
   }

   public void addMedicineReservationItem(MedicineReservationItem newMedicineReservationItem) {
      if (newMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems == null)
         this.medicineReservationItems = new ArrayList<MedicineReservationItem>();
      if (!this.medicineReservationItems.contains(newMedicineReservationItem))
         this.medicineReservationItems.add(newMedicineReservationItem);
   }

   public void removeMedicineReservationItem(MedicineReservationItem oldMedicineReservationItem) {
      if (oldMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems != null)
         if (this.medicineReservationItems.contains(oldMedicineReservationItem))
            this.medicineReservationItems.remove(oldMedicineReservationItem);
   }

   public void removeAllMedicineReservationItem() {
      if (medicineReservationItems != null)
         medicineReservationItems.clear();
   }

}