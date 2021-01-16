/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
   private List<InventoryItem> inventoryItems;
   private Pharmacy pharmacy;

   public List<InventoryItem> getInventoryItems() {
      if (inventoryItems == null)
         inventoryItems = new ArrayList<InventoryItem>();
      return inventoryItems;
   }

   public Iterator getIteratorInventoryItem() {
      if (inventoryItems == null)
         inventoryItems = new ArrayList<InventoryItem>();
      return inventoryItems.iterator();
   }

   public void setInventoryItems(List<InventoryItem> newInventoryItem) {
      removeAllInventoryItem();
      for (Iterator iter = newInventoryItem.iterator(); iter.hasNext();)
         addInventoryItem((InventoryItem)iter.next());
   }

   public void addInventoryItem(InventoryItem newInventoryItem) {
      if (newInventoryItem == null)
         return;
      if (this.inventoryItems == null)
         this.inventoryItems = new ArrayList<InventoryItem>();
      if (!this.inventoryItems.contains(newInventoryItem))
         this.inventoryItems.add(newInventoryItem);
   }

   public void removeInventoryItem(InventoryItem oldInventoryItem) {
      if (oldInventoryItem == null)
         return;
      if (this.inventoryItems != null)
         if (this.inventoryItems.contains(oldInventoryItem))
            this.inventoryItems.remove(oldInventoryItem);
   }

   public void removeAllInventoryItem() {
      if (inventoryItems != null)
         inventoryItems.clear();
   }

}