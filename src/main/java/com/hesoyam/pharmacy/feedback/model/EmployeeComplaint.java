/***********************************************************************
 * Module:  EmployeeComplaint.java
 * Author:  vule
 * Purpose: Defines the Class EmployeeComplaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Employee;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EmployeeComplaint extends Complaint {

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "employee_id")
   @NotNull(message = "Employee must be provided.")
   private Employee employee;
}