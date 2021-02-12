/***********************************************************************
 * Module:  DateTimeRange.java
 * Author:  WIN 10
 * Purpose: Defines the Class DateTimeRange
 ***********************************************************************/
package com.hesoyam.pharmacy.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class DateTimeRange {
    @Column(name= "from_date")
    @NotNull(message = "From date must be set.")
    private LocalDateTime from;
    @Column(name= "to_date")
    private LocalDateTime to;

    public DateTimeRange(){
        //Empty ctor for JSON serializer
    }

    public DateTimeRange(@NotNull(message = "From date must be set.") LocalDateTime from, LocalDateTime to) {
        if(from.isBefore(to)){
            this.from = from;
            this.to = to;
        }
        else{
            this.from = to;
            this.to = from;
        }
    }

    public DateTimeRange(LocalDate from, LocalDate to){
        this(LocalDateTime.of(from, LocalTime.MIDNIGHT), LocalDateTime.of(to, LocalTime.MIDNIGHT));
    }


    public LocalDateTime getFrom() {

      return from;
    }

    public DateTimeRange setFrom(LocalDateTime from) {
        if(this.to != null){
            if(this.to.isAfter(from)){
                this.from = from;
            }
            else{
                this.from = this.to;
                this.to = from;
            }
        }
        else{
            this.from = from;
        }
        return this;
    }

    public LocalDateTime getTo() {
      return to;
    }

    public DateTimeRange setTo(LocalDateTime to) {
        if(this.from != null){
            if(this.from.isBefore(to)){
                this.to = to;
            }
            else{
                this.to = this.from;
                this.from = to;
            }
        }
        else{
            this.to = to;
        }
        return this;
    }

    public List<LocalDate> getDays(){
        LocalDate day = from.toLocalDate();
        List<LocalDate> days = new ArrayList<>();
        while(!day.isAfter(to.toLocalDate())){
            days.add(day);
            day = day.plusDays(1);
        }

        return days;
    }

    public boolean overlaps(DateTimeRange other) {
        return this.from.isBefore(other.getTo()) && this.to.isAfter(other.getFrom());
    }

    public boolean includes(DateTimeRange other) {
        return (this.from.isBefore(other.getFrom()) || this.from.isEqual(other.getFrom())) && (this.to.isAfter(other.getTo()) || this.to.isEqual(other.getTo()));
    }

    public boolean includes(LocalDateTime localDateTime){
        return localDateTime.isAfter(this.from) && localDateTime.isBefore(this.to);
    }
}