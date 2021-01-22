/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;
import com.hesoyam.pharmacy.user.model.Administrator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private LocalDateTime deadLine;

   @Enumerated(EnumType.STRING)
   private OrderStatus orderStatus;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "administrator_id", nullable = false)
   private Administrator administrator;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   private Pharmacy pharmacy;

   @OneToMany(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
   private List<Offer> offers;

   @OneToMany(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
   private List<OrderItem> orderItems;

}