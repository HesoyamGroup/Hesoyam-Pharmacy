/***********************************************************************
 * Module:  MedicineReservationItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservationItem
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class MedicineReservationItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(0)
   private int quantity;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "medicine_id", nullable = false, referencedColumnName = "id")
   private Medicine medicine;

}