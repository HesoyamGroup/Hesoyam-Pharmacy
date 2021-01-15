/***********************************************************************
 * Module:  Sale.java
 * Author:  WIN 10
 * Purpose: Defines the Class Sale
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;

public abstract class Sale {
   protected Long id;
   protected LocalDateTime date;
   protected double price;
   
   protected Pharmacy pharmacy;

}