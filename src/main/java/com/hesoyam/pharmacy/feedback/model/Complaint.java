/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.User;

public abstract class Complaint {
   protected String text;
   protected Long id;
   protected User user;
   protected ComplaintStatus complaintStatus;
   protected Reply reply;
}