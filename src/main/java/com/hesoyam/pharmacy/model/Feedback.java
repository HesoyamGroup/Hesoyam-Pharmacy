/***********************************************************************
 * Module:  Feedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class Feedback
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public abstract class Feedback {
   protected Long id;
   protected String comment;
   protected int rating;
   protected Patient patient;

}