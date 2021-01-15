/***********************************************************************
 * Module:  Feedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class Feedback
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public abstract class Feedback {
   private Long id;
   private String comment;
   private int rating;
   
   public Patient patient;

}