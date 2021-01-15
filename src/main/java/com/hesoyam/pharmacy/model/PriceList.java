/***********************************************************************
 * Module:  PriceList.java
 * Author:  WIN 10
 * Purpose: Defines the Class PriceList
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class PriceList {
   public java.util.Collection<PriceListItem> priceListItem;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<PriceListItem> getPriceListItem() {
      if (priceListItem == null)
         priceListItem = new java.util.HashSet<PriceListItem>();
      return priceListItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPriceListItem() {
      if (priceListItem == null)
         priceListItem = new java.util.HashSet<PriceListItem>();
      return priceListItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPriceListItem */
   public void setPriceListItem(java.util.Collection<PriceListItem> newPriceListItem) {
      removeAllPriceListItem();
      for (java.util.Iterator iter = newPriceListItem.iterator(); iter.hasNext();)
         addPriceListItem((PriceListItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPriceListItem */
   public void addPriceListItem(PriceListItem newPriceListItem) {
      if (newPriceListItem == null)
         return;
      if (this.priceListItem == null)
         this.priceListItem = new java.util.HashSet<PriceListItem>();
      if (!this.priceListItem.contains(newPriceListItem))
         this.priceListItem.add(newPriceListItem);
   }
   
   /** @pdGenerated default remove
     * @param oldPriceListItem */
   public void removePriceListItem(PriceListItem oldPriceListItem) {
      if (oldPriceListItem == null)
         return;
      if (this.priceListItem != null)
         if (this.priceListItem.contains(oldPriceListItem))
            this.priceListItem.remove(oldPriceListItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPriceListItem() {
      if (priceListItem != null)
         priceListItem.clear();
   }

}