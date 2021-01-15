/***********************************************************************
 * Module:  User.java
 * Author:  vule
 * Purpose: Defines the Class User
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public abstract class User {
   protected Long id;
   protected String firstName;
   protected String lastName;
   protected String telephone;
   protected String email;
   protected String password;
   
   protected Address address;
   
   protected Role role;

}