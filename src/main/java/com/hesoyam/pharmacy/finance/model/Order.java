/***********************************************************************
 * Module:  Order.java
 * Author:  WIN 10
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private LocalDateTime deadLine;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   private Pharmacy pharmacy;

   @OneToMany(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id", referencedColumnName = "id")
   private List<Offer> offers;

   @OneToMany(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id", referencedColumnName = "id")
   private List<OrderItem> orderItems;

}