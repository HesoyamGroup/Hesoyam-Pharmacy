/***********************************************************************
 * Module:  User.java
 * Author:  vule
 * Purpose: Defines the Class User
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class User {
   protected Long userId;
   protected String firstName;
   protected String lastName;
   protected String telephone;
   protected String email;
   protected String password;
   
   protected Address address;
   
   public Role role;

}