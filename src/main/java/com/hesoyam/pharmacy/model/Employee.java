/***********************************************************************
 * Module:  Employee.java
 * Author:  WIN 10
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Employee extends User {
   private float rating;
   
   public java.util.List<Shift> shift;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Shift> getShift() {
      if (shift == null)
         shift = new java.util.ArrayList<Shift>();
      return shift;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorShift() {
      if (shift == null)
         shift = new java.util.ArrayList<Shift>();
      return shift.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newShift */
   public void setShift(java.util.List<Shift> newShift) {
      removeAllShift();
      for (java.util.Iterator iter = newShift.iterator(); iter.hasNext();)
         addShift((Shift)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newShift */
   public void addShift(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shift == null)
         this.shift = new java.util.ArrayList<Shift>();
      if (!this.shift.contains(newShift))
         this.shift.add(newShift);
   }
   
   /** @pdGenerated default remove
     * @param oldShift */
   public void removeShift(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shift != null)
         if (this.shift.contains(oldShift))
            this.shift.remove(oldShift);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllShift() {
      if (shift != null)
         shift.clear();
   }

}