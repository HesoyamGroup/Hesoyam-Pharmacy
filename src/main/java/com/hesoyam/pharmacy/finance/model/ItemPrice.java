/***********************************************************************
 * Module:  ItemPrice.java
 * Author:  WIN 10
 * Purpose: Defines the Class ItemPrice
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;


@Entity
public class ItemPrice {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @Column
   private double price;
   @Column
   private boolean isActive;

   @Embedded
   private DateTimeRange dateTimeRange;

}