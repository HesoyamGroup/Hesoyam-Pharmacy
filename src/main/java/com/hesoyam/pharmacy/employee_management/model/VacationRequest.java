/***********************************************************************
 * Module:  VacationRequest.java
 * Author:  vule
 * Purpose: Defines the Class VacationRequest
 ***********************************************************************/
package com.hesoyam.pharmacy.employee_management.model;

import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.user.model.Employee;

public class VacationRequest {
   private Long id;
   
   private VacationRequestStatus vacationRequestStatus;
   private DateTimeRange dateTimeRange;
   private Employee employee;

}