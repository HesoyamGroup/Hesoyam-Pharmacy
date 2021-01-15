/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.time.LocalDateTime;

public class Order {
   private Long id;
   private LocalDateTime deadLine;
   
   private Pharmacy pharmacy;
   private Offer[] offer;

}