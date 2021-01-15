/***********************************************************************
 * Module:  Employee.java
 * Author:  WIN 10
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public abstract class Employee extends User {
   protected float rating;
   
   protected java.util.List<Shift> shift;

   public java.util.List<Shift> getShift() {
      if (shift == null)
         shift = new java.util.ArrayList<Shift>();
      return shift;
   }

   public java.util.Iterator getIteratorShift() {
      if (shift == null)
         shift = new java.util.ArrayList<Shift>();
      return shift.iterator();
   }

   public void setShift(java.util.List<Shift> newShift) {
      removeAllShift();
      for (java.util.Iterator iter = newShift.iterator(); iter.hasNext();)
         addShift((Shift)iter.next());
   }

   public void addShift(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shift == null)
         this.shift = new java.util.ArrayList<Shift>();
      if (!this.shift.contains(newShift))
         this.shift.add(newShift);
   }

   public void removeShift(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shift != null)
         if (this.shift.contains(oldShift))
            this.shift.remove(oldShift);
   }

   public void removeAllShift() {
      if (shift != null)
         shift.clear();
   }
}