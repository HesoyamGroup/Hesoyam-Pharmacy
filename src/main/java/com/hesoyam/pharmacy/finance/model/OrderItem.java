/***********************************************************************
 * Module:  OrderItem.java
 * Author:  vule
 * Purpose: Defines the Class OrderItem
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;

@Entity
public class OrderItem {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;
   @Column
   private int quantity;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="medicine_id", nullable=false)
   private Medicine medicine;

}