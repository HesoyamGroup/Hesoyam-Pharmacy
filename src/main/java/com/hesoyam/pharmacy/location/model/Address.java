/***********************************************************************
 * Module:  Address.java
 * Author:  Geri
 * Purpose: Defines the Class Address
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import com.hesoyam.pharmacy.location.validators.latitude.LatitudeConstraint;
import com.hesoyam.pharmacy.location.validators.longitude.LongitudeConstraint;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

   @Column(length = 150)
   @NotNull
   @Length(min=3, max=150, message= "Address line length should be between 3 and 150 characters.")
   private String addressLine;

   @Column
   @LatitudeConstraint
   private Double latitude;

   @Column
   @LongitudeConstraint
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