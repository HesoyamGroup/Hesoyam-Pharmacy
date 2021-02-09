/***********************************************************************
 * Module:  Offer.java
 * Author:  WIN 10
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.user.model.Supplier;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Offer {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @Column(name = "total_price")
   @Min(0)
   private double totalPrice;

   @Column(nullable = false)
   @NotNull(message = "Delivery date must be specified.")
   private LocalDateTime deliveryDate;

   @Enumerated(EnumType.STRING)
   @Column
   private OfferStatus offerStatus;

   @ManyToOne(fetch= FetchType.LAZY, optional=false)
   @JoinColumn(name="supplier_id", nullable = false)
   private Supplier supplier;

   @ManyToOne(fetch= FetchType.LAZY, optional=false)
   @JoinColumn(name="order_id", nullable = false)
   private Order order;

   public Offer() {
   }

   public Offer(double totalPrice, LocalDateTime deliveryDate) {
      this.totalPrice = totalPrice;
      this.deliveryDate = deliveryDate;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(double totalPrice) {
      this.totalPrice = totalPrice;
   }

   public LocalDateTime getDeliveryDate() {
      return deliveryDate;
   }

   public void setDeliveryDate(LocalDateTime deliveryDate) {
      this.deliveryDate = deliveryDate;
   }

   public OfferStatus getOfferStatus() {
      return offerStatus;
   }

   public void setOfferStatus(OfferStatus offerStatus) {
      this.offerStatus = offerStatus;
   }

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

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public boolean accept() {
      if(this.offerStatus == OfferStatus.CREATED){
         return getOrder().accept(this);
      }else
         return false;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Offer)) return false;
      Offer offer = (Offer) o;
      return Objects.equals(getId(), offer.getId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId());
   }

   public boolean isAccepted() {
      return offerStatus == OfferStatus.ACCEPTED;
   }

   public boolean isRejected(){
      return offerStatus == OfferStatus.REJECTED;
   }
}