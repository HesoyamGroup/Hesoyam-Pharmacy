/***********************************************************************
 * Module:  PriceList.java
 * Author:  WIN 10
 * Purpose: Defines the Class PriceList
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriceList {
   private List<PriceListItem> priceListItems;

   public List<PriceListItem> getPriceListItems() {
      if (priceListItems == null)
         priceListItems = new ArrayList<PriceListItem>();
      return priceListItems;
   }

   public Iterator getIteratorPriceListItem() {
      if (priceListItems == null)
         priceListItems = new ArrayList<PriceListItem>();
      return priceListItems.iterator();
   }

   public void setPriceListItems(List<PriceListItem> newPriceListItem) {
      removeAllPriceListItem();
      for (Iterator iter = newPriceListItem.iterator(); iter.hasNext();)
         addPriceListItem((PriceListItem)iter.next());
   }

   public void addPriceListItem(PriceListItem newPriceListItem) {
      if (newPriceListItem == null)
         return;
      if (this.priceListItems == null)
         this.priceListItems = new ArrayList<PriceListItem>();
      if (!this.priceListItems.contains(newPriceListItem))
         this.priceListItems.add(newPriceListItem);
   }

   public void removePriceListItem(PriceListItem oldPriceListItem) {
      if (oldPriceListItem == null)
         return;
      if (this.priceListItems != null)
         if (this.priceListItems.contains(oldPriceListItem))
            this.priceListItems.remove(oldPriceListItem);
   }

   public void removeAllPriceListItem() {
      if (priceListItems != null)
         priceListItems.clear();
   }

}