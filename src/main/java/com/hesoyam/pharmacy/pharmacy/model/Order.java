/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.user.model.Administrator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   @NotNull(message = "Deadline must be specified.")
   private LocalDateTime deadLine;

   @Enumerated(EnumType.STRING)
   private OrderStatus orderStatus;

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name = "administrator_id", nullable = false)
   private Administrator administrator;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   private Pharmacy pharmacy;

//   @OneToMany(fetch=FetchType.LAZY)
//   @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
   @OneToMany(fetch = FetchType.LAZY, mappedBy="order")
   private List<Offer> offers;

   @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
   private List<OrderItem> orderItems;


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getDeadLine() {
      return deadLine;
   }

   public void setDeadLine(LocalDateTime deadLine) {
      this.deadLine = deadLine;
   }

   public OrderStatus getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
   }

   public Administrator getAdministrator() {
      return administrator;
   }

   public void setAdministrator(Administrator administrator) {
      this.administrator = administrator;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   public List<Offer> getOffers() {
      if(offers == null)
         return new ArrayList<>();
      return offers;
   }

   public void setOffers(List<Offer> offers) {
      this.offers = offers;
   }

   public List<OrderItem> getOrderItems() {
      if(orderItems == null)
         return new ArrayList<>();
      return orderItems;
   }

   public void setOrderItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
   }

   public boolean isOfferValidForOrder(Offer offer){
      for(Offer off : this.offers){
         if(off.getSupplier().getId().equals(offer.getSupplier().getId()) && off.getOfferStatus() == OfferStatus.CREATED){
            return false;
         }
      }
      LocalDateTime deliveryDate = offer.getDeliveryDate();
      return deliveryDate.compareTo(deadLine) < 0 && deliveryDate.compareTo(LocalDate.now().atTime(0,0,0)) >= 0 && orderStatus == OrderStatus.CREATED;
   }

   public boolean isOfferEditable(Offer offer){
      return LocalDate.now().atTime(0, 0, 0).compareTo(deadLine) < 0 && offer.getOfferStatus() == OfferStatus.CREATED;
      //TODO: Check if today date is before date specified as the last day for canceling.
   }

   public boolean hasOffers() {
      return !getOffers().isEmpty();
   }

   public void updateDeadline(LocalDateTime deadline) {
      if(deadLine.isAfter(LocalDateTime.now()) && orderStatus == OrderStatus.CREATED)
         setDeadLine(deadline);
      else
         throw new IllegalStateException();
   }
}