/***********************************************************************
 * Module:  Promotion.java
 * Author:  WIN 10
 * Purpose: Defines the Class Promotion
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.user.model.Administrator;

import javax.persistence.*;

@Entity
public class Promotion {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length=350)
   private String text;

   @Embedded
   private DateTimeRange dateTimeRange;
   @OneToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name="administrator_id")
   private Administrator administrator;
}