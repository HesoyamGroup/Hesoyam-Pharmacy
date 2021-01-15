/***********************************************************************
 * Module:  Therapy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Therapy
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Therapy {
   private java.util.Collection<TherapyItem> therapyItem;

   public java.util.Collection<TherapyItem> getTherapyItem() {
      if (therapyItem == null)
         therapyItem = new java.util.HashSet<TherapyItem>();
      return therapyItem;
   }

   public java.util.Iterator getIteratorTherapyItem() {
      if (therapyItem == null)
         therapyItem = new java.util.HashSet<TherapyItem>();
      return therapyItem.iterator();
   }

   public void setTherapyItem(java.util.Collection<TherapyItem> newTherapyItem) {
      removeAllTherapyItem();
      for (java.util.Iterator iter = newTherapyItem.iterator(); iter.hasNext();)
         addTherapyItem((TherapyItem)iter.next());
   }

   public void addTherapyItem(TherapyItem newTherapyItem) {
      if (newTherapyItem == null)
         return;
      if (this.therapyItem == null)
         this.therapyItem = new java.util.HashSet<TherapyItem>();
      if (!this.therapyItem.contains(newTherapyItem))
         this.therapyItem.add(newTherapyItem);
   }

   public void removeTherapyItem(TherapyItem oldTherapyItem) {
      if (oldTherapyItem == null)
         return;
      if (this.therapyItem != null)
         if (this.therapyItem.contains(oldTherapyItem))
            this.therapyItem.remove(oldTherapyItem);
   }

   public void removeAllTherapyItem() {
      if (therapyItem != null)
         therapyItem.clear();
   }

}