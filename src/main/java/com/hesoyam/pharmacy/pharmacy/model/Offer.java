/***********************************************************************
 * Module:  Offer.java
 * Author:  WIN 10
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.user.model.Supplier;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Offer {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @Column(name = "total_price")
   private double totalPrice;

   @Column(nullable = false)
   private LocalDateTime deliveryDate;

   @Enumerated(EnumType.STRING)
   @Column
   private OfferStatus offerStatus;

   @ManyToOne(fetch= FetchType.LAZY, optional=false)
   @JoinColumn(name="supplier_id", nullable = false)
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