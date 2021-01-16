/***********************************************************************
 * Module:  PrescriptionItem.java
 * Author:  Geri
 * Purpose: Defines the Class PrescriptionItem
 ***********************************************************************/
package com.hesoyam.pharmacy.prescription.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;

@Entity
public class PrescriptionItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int quantity;

   @ManyToOne(fetch = FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id")
   private Medicine medicine;

}