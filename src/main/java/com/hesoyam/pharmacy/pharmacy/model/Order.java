/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hesoyam.pharmacy.user.model.Administrator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

   @OneToMany(fetch=FetchType.LAZY)
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
      return offers;
   }

   public void setOffers(List<Offer> offers) {
      this.offers = offers;
   }

   public List<OrderItem> getOrderItems() {
      return orderItems;
   }

   public void setOrderItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
   }

   public boolean isOfferValidForOrder(Offer offer){
      for(Offer off : this.offers){
         if(off.getSupplier().getId().equals(offer.getSupplier().getId())){
            return false;
         }
      }
      LocalDateTime deliveryDate = offer.getDeliveryDate();
      return deliveryDate.compareTo(deadLine) < 0 && deliveryDate.compareTo(LocalDateTime.now()) > 0 && orderStatus == OrderStatus.CREATED;
   }
}