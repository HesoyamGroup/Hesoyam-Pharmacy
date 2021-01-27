/***********************************************************************
 * Module:  City.java
 * Author:  Geri
 * Purpose: Defines the Class City
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class City {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 100)
   @NotNull
   @Length(min=3, max=100, message= "City name length should be between 3 and 100 characters.")
   private String cityName;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name="country_id", referencedColumnName = "id")
   @JsonBackReference
   private Country country;

   public Long getId() {
      return id;
   }

   public String getCityName() {
      return cityName;
   }

   public Country getCountry() {
      return country;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setCityName(String cityName) {
      this.cityName = cityName;
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