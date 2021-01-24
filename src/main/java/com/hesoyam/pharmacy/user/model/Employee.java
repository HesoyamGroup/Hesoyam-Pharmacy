/***********************************************************************
 * Module:  Employee.java
 * Author:  WIN 10
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;
import com.hesoyam.pharmacy.employee_management.model.Shift;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Employee extends User {
   @Column
   @Min(0)
   //TODO: Add max rating(5 vs 10 scale)
   protected double rating;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
      if (!this.shifts.contains(newShift))
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
}