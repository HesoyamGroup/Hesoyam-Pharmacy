/***********************************************************************
 * Module:  Sale.java
 * Author:  WIN 10
 * Purpose: Defines the Class Sale
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sale {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;
   @Column(nullable = false)
   @NotNull(message = "Sale date must be specified.")
   protected LocalDateTime dateOfSale;
   @Column
   @Min(0)
   protected double price;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   protected Pharmacy pharmacy;

}