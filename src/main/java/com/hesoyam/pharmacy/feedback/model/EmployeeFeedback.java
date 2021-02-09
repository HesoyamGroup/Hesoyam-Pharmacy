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

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "employee_id")
   private Employee employee;


   public EmployeeFeedback() {
   }

   public EmployeeFeedback(Employee employee) {
      this.employee = employee;
   }

   public Employee getEmployee() {
      return employee;
   }

   public void setEmployee(Employee employee) {
      this.employee = employee;
   }

   public void update(EmployeeFeedback employeeFeedback){
      this.employee = employeeFeedback.getEmployee();
      this.comment = employeeFeedback.getComment();
      this.patient = employeeFeedback.getPatient();
      this.rating = employeeFeedback.getRating();
   }
}