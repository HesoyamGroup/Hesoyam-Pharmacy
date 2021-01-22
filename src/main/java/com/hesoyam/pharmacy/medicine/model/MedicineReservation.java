/***********************************************************************
 * Module:  MedicineReservation.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservation
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import com.hesoyam.pharmacy.user.model.Patient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class MedicineReservation {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "pick_up_date")
   private LocalDateTime pickUpDate;

   @Column(length =  50)
   private String code;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "medicine_reservation_id", referencedColumnName = "id")
   private List<MedicineReservationItem> medicineReservationItems;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @Enumerated(EnumType.STRING)
   private MedicineReservationStatus medicineReservationStatus;

   public List<MedicineReservationItem> getMedicineReservationItems() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<>();
      return medicineReservationItems;
   }

   public Iterator<MedicineReservationItem> getIteratorMedicineReservationItem() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<>();
      return medicineReservationItems.iterator();
   }

   public void setMedicineReservationItems(List<MedicineReservationItem> newMedicineReservationItem) {
      removeAllMedicineReservationItem();
      for (Iterator<MedicineReservationItem> iter = newMedicineReservationItem.iterator(); iter.hasNext();)
         addMedicineReservationItem(iter.next());
   }

   public void addMedicineReservationItem(MedicineReservationItem newMedicineReservationItem) {
      if (newMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems == null)
         this.medicineReservationItems = new ArrayList<>();
      if (!this.medicineReservationItems.contains(newMedicineReservationItem))
         this.medicineReservationItems.add(newMedicineReservationItem);
   }

   public void removeMedicineReservationItem(MedicineReservationItem oldMedicineReservationItem) {
      if (oldMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems != null && this.medicineReservationItems.contains(oldMedicineReservationItem))
            this.medicineReservationItems.remove(oldMedicineReservationItem);
   }

   public void removeAllMedicineReservationItem() {
      if (medicineReservationItems != null)
         medicineReservationItems.clear();
   }

}