/***********************************************************************
 * Module:  Employee.java
 * Author:  WIN 10
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.model.ShiftType;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Employee extends User {
   @Column
   @Min(0)
   //TODO: Add max rating(5 vs 10 scale)
   protected double rating;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name="employee_id", referencedColumnName="id")
   protected List<Shift> shifts;

   public List<Shift> getShifts() {
      if (shifts == null)
         shifts = new ArrayList<>();
      return shifts;
   }

   public Iterator<Shift> getIteratorShift() {
      if (shifts == null)
         shifts = new ArrayList<>();
      return shifts.iterator();
   }

   public void setShifts(List<Shift> newShift) {
      removeAllShift();
      for (Iterator<Shift> iter = newShift.iterator(); iter.hasNext();)
         addShift(iter.next());
   }

   public void addShift(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shifts == null)
         this.shifts = new ArrayList<>();
      this.shifts.add(newShift);
   }

   public void removeShift(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shifts != null && this.shifts.contains(oldShift))
         this.shifts.remove(oldShift);
   }

   public void removeAllShift() {
      if (shifts != null)
         shifts.clear();
   }

   public boolean generateShifts(DateTimeRange dayRange, LocalTime shiftFrom, LocalTime shiftTo, Pharmacy pharmacy){
      for (LocalDate day: dayRange.getDays()) {
         DateTimeRange dateTimeRange = new DateTimeRange(day.atTime(shiftFrom), day.atTime(shiftTo));
         if(getShifts().stream().noneMatch(shift -> shift.getDateTimeRange().overlaps(dateTimeRange))){
            Shift shift = new Shift(dateTimeRange, ShiftType.WORK, pharmacy);
            addShift(shift);
         } else
            return false;
      }
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public double getRating() {
      return rating;
   }

   public void setRating(double rating) {
      this.rating = rating;
   }

   public abstract boolean isAdministratorMyBoss(User administrator);

   public abstract boolean isWorkingAt(Pharmacy pharmacy);

   protected boolean isAtWork(DateTimeRange dateTimeRange, Pharmacy pharmacy){
      return getShifts().stream().anyMatch(shift -> shift.isAvailableFor(dateTimeRange, pharmacy));
   }

   protected abstract boolean hasClearSchedule(DateTimeRange dateTimeRange);

   public boolean isAvailable(DateTimeRange dateTimeRange, Pharmacy pharmacy){
      return isAtWork(dateTimeRange, pharmacy) && hasClearSchedule(dateTimeRange);
   }

   public void clearShiftsFor(DateTimeRange dateTimeRange){
      List<Shift> shiftsToRemove = shifts.stream().filter(shift -> shift.getType() == ShiftType.WORK && shift.getDateTimeRange().overlaps(dateTimeRange)).collect(Collectors.toList());
      this.shifts.removeAll(shiftsToRemove);
   }

   public abstract void addVacation(VacationRequest vacationRequest);
}