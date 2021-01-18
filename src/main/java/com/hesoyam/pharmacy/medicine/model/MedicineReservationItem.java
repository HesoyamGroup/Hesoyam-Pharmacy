/***********************************************************************
 * Module:  MedicineReservationItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservationItem
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;

@Entity
public class MedicineReservationItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int quantity;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "medicine_id", nullable = false, referencedColumnName = "id")
   private Medicine medicine;

}