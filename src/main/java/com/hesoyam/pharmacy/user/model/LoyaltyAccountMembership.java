/***********************************************************************
 * Module:  LoyaltyAccountMembership.java
 * Author:  WIN 10
 * Purpose: Defines the Class LoyaltyAccountMembership
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import javax.persistence.*;

@Entity
public class LoyaltyAccountMembership {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true, nullable = false)
   private String name;

   @Column
   private double discount;

   @Column
   private int minPoints;

}