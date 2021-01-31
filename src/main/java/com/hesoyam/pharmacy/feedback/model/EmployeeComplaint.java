/***********************************************************************
 * Module:  EmployeeComplaint.java
 * Author:  vule
 * Purpose: Defines the Class EmployeeComplaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.Patient;

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
   @JsonIgnoreProperties({"pharmacies", "checkUps", "counselings", "pharmacy", "iteratorPharmacy", "shifts", "iteratorShift"})
   private Employee employee;

   public EmployeeComplaint(){}

   public EmployeeComplaint(String body, Patient patient, ComplaintStatus complaintStatus, Employee employee){
      super(body, patient, complaintStatus);
      this.employee = employee;
   }

   public Employee getEmployee() {
      return employee;
   }

   public void setEmployee(Employee employee) {
      this.employee = employee;
   }

   @Override
   public String getEntityName(){
      return employee.getFirstName() + " " + employee.getLastName();
   }
}