/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
   private Long id;
   private LocalDateTime deadLine;
   
   private Pharmacy pharmacy;
   private List<Offer> offers;

}