/***********************************************************************
 * Module:  Offer.java
 * Author:  WIN 10
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.time.LocalDateTime;

public class Offer {
   private double price;
   private LocalDateTime deliveryDate;
   
   private OfferStatus offerStatus;
   private Supplier supplier;

   public Supplier getSupplier() {
      return supplier;
   }

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