/***********************************************************************
 * Module:  User.java
 * Author:  vule
 * Purpose: Defines the Class User
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.location.model.Address;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length=100, name = "first_name")
   protected String firstName;

   @Column(length=100, name="last_name")
   protected String lastName;

   @Column(length=20)
   protected String telephone;

   @Column(length=50)
   protected String email;

   @Enumerated(EnumType.STRING)
   protected Gender gender;

   //TODO: Password encrypt (Spring Security)
   @Column(length=75)
   protected String password;

   //TODO: Potentially to be changed. (Spring Security)
   @Enumerated(EnumType.STRING)
   @Column
   protected Role role;

   @Embedded
   protected Address address;

}