/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Inventory {
   private java.util.Collection<InventoryItem> inventoryItem;
   private Pharmacy pharmacy;

   public java.util.Collection<InventoryItem> getInvetoryItem() {
      if (inventoryItem == null)
         inventoryItem = new java.util.HashSet<InventoryItem>();
      return inventoryItem;
   }

   public java.util.Iterator getIteratorInvetoryItem() {
      if (inventoryItem == null)
         inventoryItem = new java.util.HashSet<InventoryItem>();
      return inventoryItem.iterator();
   }

   public void setInvetoryItem(java.util.Collection<InventoryItem> newInventoryItem) {
      removeAllInvetoryItem();
      for (java.util.Iterator iter = newInventoryItem.iterator(); iter.hasNext();)
         addInvetoryItem((InventoryItem)iter.next());
   }

   public void addInvetoryItem(InventoryItem newInventoryItem) {
      if (newInventoryItem == null)
         return;
      if (this.inventoryItem == null)
         this.inventoryItem = new java.util.HashSet<InventoryItem>();
      if (!this.inventoryItem.contains(newInventoryItem))
         this.inventoryItem.add(newInventoryItem);
   }

   public void removeInvetoryItem(InventoryItem oldInventoryItem) {
      if (oldInventoryItem == null)
         return;
      if (this.inventoryItem != null)
         if (this.inventoryItem.contains(oldInventoryItem))
            this.inventoryItem.remove(oldInventoryItem);
   }

   public void removeAllInvetoryItem() {
      if (inventoryItem != null)
         inventoryItem.clear();
   }

}