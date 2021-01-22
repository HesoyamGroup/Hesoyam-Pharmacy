/***********************************************************************
 * Module:  Promotion.java
 * Author:  WIN 10
 * Purpose: Defines the Class Promotion
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.user.model.Administrator;

import javax.persistence.*;

@Entity
public class Promotion {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length=350)
   private String description;

   @Embedded
   private DateTimeRange dateTimeRange;

   @OneToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name="administrator_id", nullable = false)
   private Administrator administrator;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "pharmacy_id", nullable = false)
   private Pharmacy pharmacy;
}