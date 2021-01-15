/***********************************************************************
 * Module:  VacationRequest.java
 * Author:  vule
 * Purpose: Defines the Class VacationRequest
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class VacationRequest {
   private Long id;
   
   public VacationRequestStatus vacationRequestStatus;
   public DateTimeRange dateTimeRange;
   public Employee employee;

}