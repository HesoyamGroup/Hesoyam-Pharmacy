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


   public Address(){}

   public String getAddressLine() {
      return addressLine;
   }

   public void setAddressLine(String addressLine) {
      this.addressLine = addressLine;
   }

   public Double getLatitude() {
      return latitude;
   }

   public void setLatitude(Double latitude) {
      this.latitude = latitude;
   }

   public Double getLongitude() {
      return longitude;
   }

   public void setLongitude(Double longitude) {
      this.longitude = longitude;
   }

   public City getCity() {
      return city;
   }

   public void setCity(City city) {
      this.city = city;
   }
}