/***********************************************************************
 * Module:  Therapy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Therapy
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Therapy {
   public java.util.Collection<TherapyItem> therapyItem;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<TherapyItem> getTherapyItem() {
      if (therapyItem == null)
         therapyItem = new java.util.HashSet<TherapyItem>();
      return therapyItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTherapyItem() {
      if (therapyItem == null)
         therapyItem = new java.util.HashSet<TherapyItem>();
      return therapyItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTherapyItem */
   public void setTherapyItem(java.util.Collection<TherapyItem> newTherapyItem) {
      removeAllTherapyItem();
      for (java.util.Iterator iter = newTherapyItem.iterator(); iter.hasNext();)
         addTherapyItem((TherapyItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTherapyItem */
   public void addTherapyItem(TherapyItem newTherapyItem) {
      if (newTherapyItem == null)
         return;
      if (this.therapyItem == null)
         this.therapyItem = new java.util.HashSet<TherapyItem>();
      if (!this.therapyItem.contains(newTherapyItem))
         this.therapyItem.add(newTherapyItem);
   }
   
   /** @pdGenerated default remove
     * @param oldTherapyItem */
   public void removeTherapyItem(TherapyItem oldTherapyItem) {
      if (oldTherapyItem == null)
         return;
      if (this.therapyItem != null)
         if (this.therapyItem.contains(oldTherapyItem))
            this.therapyItem.remove(oldTherapyItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTherapyItem() {
      if (therapyItem != null)
         therapyItem.clear();
   }

}