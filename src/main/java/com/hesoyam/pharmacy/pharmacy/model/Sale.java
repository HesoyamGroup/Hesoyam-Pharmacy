/***********************************************************************
 * Module:  Sale.java
 * Author:  WIN 10
 * Purpose: Defines the Class Sale
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sale {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;
   @Column(nullable = false)
   protected LocalDateTime dateOfSale;
   @Column
   protected double price;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   protected Pharmacy pharmacy;

}