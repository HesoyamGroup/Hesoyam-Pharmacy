/***********************************************************************
 * Module:  Offer.java
 * Author:  WIN 10
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Offer {
   private double price;
   private Date deliveryDate;
   
   private OfferStatus offerStatus;
   private Supplier supplier;
   
   
   /** @pdGenerated default parent getter */
   public Supplier getSupplier() {
      return supplier;
   }
   
   /** @pdGenerated default parent setter
     * @param newSupplier */
   public void setSupplier(Supplier newSupplier) {
      if (this.supplier == null || !this.supplier.equals(newSupplier))
      {
         if (this.supplier != null)
         {
            Supplier oldSupplier = this.supplier;
            this.supplier = null;
            oldSupplier.removeOffer(this);
         }
         if (newSupplier != null)
         {
            this.supplier = newSupplier;
            this.supplier.addOffer(this);
         }
      }
   }

}