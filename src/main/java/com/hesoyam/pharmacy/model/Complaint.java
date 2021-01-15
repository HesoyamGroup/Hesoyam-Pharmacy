/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public abstract class Complaint {
   private String text;
   private Long id;
   
   public User user;
   public ComplaintStatus complaintStatus;
   public Reply reply;

}