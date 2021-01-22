/***********************************************************************
 * Module:  VacationRequest.java
 * Author:  vule
 * Purpose: Defines the Class VacationRequest
 ***********************************************************************/
package com.hesoyam.pharmacy.employee_management.model;

import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.user.model.Employee;

import javax.persistence.*;

@Entity
public class VacationRequest {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Enumerated(EnumType.STRING)
   private VacationRequestStatus vacationRequestStatus;

   @Embedded
   private DateTimeRange dateTimeRange;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name="employee_id", nullable = false)
   private Employee employee;

}