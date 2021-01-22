/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Inventory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "inventory_id", referencedColumnName = "id", nullable = false)
   private List<InventoryItem> inventoryItems;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="pharmacy_id", nullable = false)
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