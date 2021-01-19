/***********************************************************************
 * Module:  LoyaltyAccount.java
 * Author:  WIN 10
 * Purpose: Defines the Class LoyaltyAccount
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import javax.persistence.*;

@Entity
public class LoyaltyAccount {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   @Column
   private int points;

   @OneToOne(optional=false, fetch = FetchType.EAGER)
   @JoinColumn(name="patient_id", nullable = false)
   private Patient patient;

   @ManyToOne(optional = false, fetch = FetchType.EAGER)
   @JoinColumn(name="membership_id", nullable = false)
   private LoyaltyAccountMembership loyaltyAccountMembership;

}