/***********************************************************************
 * Module:  InvetoryItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class InvetoryItem
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import java.util.*;

@Entity
public class InventoryItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int available;
   @Column
   private int reserved;

   @ManyToOne(fetch=FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id")
   private Medicine medicine;

   @OneToMany(mappedBy = "inventoryItem")
   private List<InventoryPriceItem> prices;

   public List<InventoryPriceItem> getPrices() {
      if (prices == null)
         prices = new ArrayList<>();
      return prices;
   }

   public Iterator<InventoryPriceItem> getIteratorPrices() {
      if (prices == null)
         prices = new ArrayList<>();
      return prices.iterator();
   }

   public void setPrices(List<InventoryPriceItem> newPrices) {
      removeAllPrices();
      for (Iterator<InventoryPriceItem> iter = newPrices.iterator(); iter.hasNext();)
         addPrices(iter.next());
   }

   public void addPrices(InventoryPriceItem newInventoryPriceItem) {
      if (newInventoryPriceItem == null)
         return;
      if (this.prices == null)
         this.prices = new ArrayList<>();
      if (!this.prices.contains(newInventoryPriceItem)) {
         this.prices.add(newInventoryPriceItem);
         newInventoryPriceItem.setInventoryItem(this);
      }
   }

   public void removePrices(InventoryPriceItem oldInventoryPriceItem) {
      if (oldInventoryPriceItem == null)
         return;
      if (this.prices != null && this.prices.contains(oldInventoryPriceItem)) {
         this.prices.remove(oldInventoryPriceItem);
         oldInventoryPriceItem.setInventoryItem((InventoryItem)null);
      }
   }

   public void removeAllPrices() {
      if (prices != null) {
         InventoryPriceItem oldInventoryPriceItem;
         for (Iterator<InventoryPriceItem> iter = getIteratorPrices(); iter.hasNext();) {
            oldInventoryPriceItem = iter.next();
            iter.remove();
            oldInventoryPriceItem.setInventoryItem((InventoryItem)null);
         }
      }
   }

}