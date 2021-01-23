/***********************************************************************
 * Module:  City.java
 * Author:  Geri
 * Purpose: Defines the Class City
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class City {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 100)
   private String cityName;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name="country_id", referencedColumnName = "id")
   @JsonBackReference
   private Country country;

   public City(){}

   public Long getId() {
      return id;
   }

   public String getCityName() {
      return cityName;
   }

   public Country getCountry() {
      return country;
   }

   public void setCountry(Country newCountry) {
      if (this.country == null || !this.country.equals(newCountry))
      {
         if (this.country != null)
         {
            Country oldCountry = this.country;
            this.country = null;
            oldCountry.removeCity(this);
         }
         if (newCountry != null)
         {
            this.country = newCountry;
            this.country.addCity(this);
         }
      }
   }
}