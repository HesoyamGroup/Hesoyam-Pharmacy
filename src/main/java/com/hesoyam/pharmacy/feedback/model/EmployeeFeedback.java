/***********************************************************************
 * Module:  DermatologistFeedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class DermatologistFeedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Employee;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeFeedback extends Feedback {

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "employee_id")
   private Employee employee;

}