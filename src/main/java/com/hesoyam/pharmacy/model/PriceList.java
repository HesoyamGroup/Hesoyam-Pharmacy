/***********************************************************************
 * Module:  PriceList.java
 * Author:  WIN 10
 * Purpose: Defines the Class PriceList
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class PriceList {
   private java.util.Collection<PriceListItem> priceListItem;

   public java.util.Collection<PriceListItem> getPriceListItem() {
      if (priceListItem == null)
         priceListItem = new java.util.HashSet<PriceListItem>();
      return priceListItem;
   }

   public java.util.Iterator getIteratorPriceListItem() {
      if (priceListItem == null)
         priceListItem = new java.util.HashSet<PriceListItem>();
      return priceListItem.iterator();
   }

   public void setPriceListItem(java.util.Collection<PriceListItem> newPriceListItem) {
      removeAllPriceListItem();
      for (java.util.Iterator iter = newPriceListItem.iterator(); iter.hasNext();)
         addPriceListItem((PriceListItem)iter.next());
   }

   public void addPriceListItem(PriceListItem newPriceListItem) {
      if (newPriceListItem == null)
         return;
      if (this.priceListItem == null)
         this.priceListItem = new java.util.HashSet<PriceListItem>();
      if (!this.priceListItem.contains(newPriceListItem))
         this.priceListItem.add(newPriceListItem);
   }

   public void removePriceListItem(PriceListItem oldPriceListItem) {
      if (oldPriceListItem == null)
         return;
      if (this.priceListItem != null)
         if (this.priceListItem.contains(oldPriceListItem))
            this.priceListItem.remove(oldPriceListItem);
   }

   public void removeAllPriceListItem() {
      if (priceListItem != null)
         priceListItem.clear();
   }

}