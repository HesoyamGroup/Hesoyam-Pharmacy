/***********************************************************************
 * Module:  PriceListItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class PriceListItem
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriceListItem {
   protected List<ItemPrice> itemPrices;

   public List<ItemPrice> getItemPrices() {
      if (itemPrices == null)
         itemPrices = new ArrayList<ItemPrice>();
      return itemPrices;
   }

   public Iterator getIteratorItemPrice() {
      if (itemPrices == null)
         itemPrices = new ArrayList<ItemPrice>();
      return itemPrices.iterator();
   }

   public void setItemPrices(List<ItemPrice> newItemPrice) {
      removeAllItemPrice();
      for (Iterator iter = newItemPrice.iterator(); iter.hasNext();)
         addItemPrice((ItemPrice)iter.next());
   }

   public void addItemPrice(ItemPrice newItemPrice) {
      if (newItemPrice == null)
         return;
      if (this.itemPrices == null)
         this.itemPrices = new ArrayList<ItemPrice>();
      if (!this.itemPrices.contains(newItemPrice))
         this.itemPrices.add(newItemPrice);
   }

   public void removeItemPrice(ItemPrice oldItemPrice) {
      if (oldItemPrice == null)
         return;
      if (this.itemPrices != null)
         if (this.itemPrices.contains(oldItemPrice))
            this.itemPrices.remove(oldItemPrice);
   }

   public void removeAllItemPrice() {
      if (itemPrices != null)
         itemPrices.clear();
   }

}