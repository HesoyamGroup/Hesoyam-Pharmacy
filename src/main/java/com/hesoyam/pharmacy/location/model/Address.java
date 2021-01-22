/***********************************************************************
 * Module:  Address.java
 * Author:  Geri
 * Purpose: Defines the Class Address
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import javax.persistence.*;

@Embeddable
public class Address {

   @Column(length = 150)
   private String addressLine;

   @Column
   private Double latitude;

   @Column
   private Double longitude;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="city_id")
   private City city;
}