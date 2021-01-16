/***********************************************************************
 * Module:  TherapyItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class TherapyItem
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;

@Entity
public class TherapyItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int quantity;
   @Column
   private int numberOfDays;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="medicine_id", referencedColumnName="id")
   private Medicine medicine;

}