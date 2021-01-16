/***********************************************************************
 * Module:  InvetoryItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class InvetoryItem
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;

@Entity
public class InventoryItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int available;
   @Column
   private int reserved;

   @ManyToOne(fetch=FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id")
   private Medicine medicine;
}