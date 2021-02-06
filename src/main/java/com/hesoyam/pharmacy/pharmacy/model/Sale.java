/***********************************************************************
 * Module:  Sale.java
 * Author:  WIN 10
 * Purpose: Defines the Class Sale
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sale {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;
   @Column(nullable = false)
   @NotNull(message = "Sale date must be specified.")
   protected LocalDateTime dateOfSale;
   @Column
   @Min(0)
   protected double price;

   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="pharmacy_id", nullable = false)
   @JsonManagedReference
   protected Pharmacy pharmacy;

   public Sale() {
   }

   public Sale(LocalDateTime dateOfSale, double price, Pharmacy pharmacy) {
      this.dateOfSale = dateOfSale;
      this.price = price;
      this.pharmacy = pharmacy;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getDateOfSale() {
      return dateOfSale;
   }

   public void setDateOfSale(LocalDateTime dateOfSale) {
      this.dateOfSale = dateOfSale;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }
}