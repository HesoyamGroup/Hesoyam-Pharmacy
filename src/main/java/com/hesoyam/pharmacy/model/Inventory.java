/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Inventory {
   private java.util.Collection<InvetoryItem> invetoryItem;
   private Pharmacy pharmacy;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<InvetoryItem> getInvetoryItem() {
      if (invetoryItem == null)
         invetoryItem = new java.util.HashSet<InvetoryItem>();
      return invetoryItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorInvetoryItem() {
      if (invetoryItem == null)
         invetoryItem = new java.util.HashSet<InvetoryItem>();
      return invetoryItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newInvetoryItem */
   public void setInvetoryItem(java.util.Collection<InvetoryItem> newInvetoryItem) {
      removeAllInvetoryItem();
      for (java.util.Iterator iter = newInvetoryItem.iterator(); iter.hasNext();)
         addInvetoryItem((InvetoryItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newInvetoryItem */
   public void addInvetoryItem(InvetoryItem newInvetoryItem) {
      if (newInvetoryItem == null)
         return;
      if (this.invetoryItem == null)
         this.invetoryItem = new java.util.HashSet<InvetoryItem>();
      if (!this.invetoryItem.contains(newInvetoryItem))
         this.invetoryItem.add(newInvetoryItem);
   }
   
   /** @pdGenerated default remove
     * @param oldInvetoryItem */
   public void removeInvetoryItem(InvetoryItem oldInvetoryItem) {
      if (oldInvetoryItem == null)
         return;
      if (this.invetoryItem != null)
         if (this.invetoryItem.contains(oldInvetoryItem))
            this.invetoryItem.remove(oldInvetoryItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllInvetoryItem() {
      if (invetoryItem != null)
         invetoryItem.clear();
   }

}