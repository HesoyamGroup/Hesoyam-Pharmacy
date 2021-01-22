/***********************************************************************
 * Module:  DateTimeRange.java
 * Author:  WIN 10
 * Purpose: Defines the Class DateTimeRange
 ***********************************************************************/
package com.hesoyam.pharmacy.util;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class DateTimeRange {
   @Column(name= "from_date")
   private LocalDateTime from;
   @Column(name= "to_date")
   private LocalDateTime to;

   public DateTimeRange(){}

   public LocalDateTime getFrom() {
      return from;
   }

   public DateTimeRange setFrom(LocalDateTime from) {
      this.from = from;
      return this;
   }

   public LocalDateTime getTo() {
      return to;
   }

   public DateTimeRange setTo(LocalDateTime to) {
      this.to = to;
      return this;
   }

}