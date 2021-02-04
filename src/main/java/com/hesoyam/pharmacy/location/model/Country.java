/***********************************************************************
 * Module:  Country.java
 * Author:  Geri
 * Purpose: Defines the Class Country
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Country {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true, length = 3, nullable = false)
   @NotNull
   @Length(min=2, max=3, message= "Country code length should be between 2 and 3 characters. (inclusive)")
   private String countryCode;

   @Column(length = 100, unique = true)
   @NotNull
   @Length(min=3, max=100, message= "Country name length should be between 3 and 100 characters.")
   private String countryName;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "country")
   @JsonManagedReference
   private List<City> cities;


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCountryCode() {
      return countryCode;
   }

   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }

   public String getCountryName() {
      return countryName;
   }

   public void setCountryName(String countryName) {
      this.countryName = countryName;
   }


   public List<City> getCities() {
      if (cities == null)
         cities = new ArrayList<>();
      return cities;
   }

   public Iterator<City> getIteratorCity() {
      if (cities == null)
         cities = new ArrayList<>();
      return cities.iterator();
   }

   public void setCities(List<City> newCity) {
      removeAllCity();
      for (Iterator<City> iter = newCity.iterator(); iter.hasNext();)
         addCity(iter.next());
   }

   public void addCity(City newCity) {
      if (newCity == null)
         return;
      if (this.cities == null)
         this.cities = new ArrayList<>();
      if (!this.cities.contains(newCity))
      {
         this.cities.add(newCity);
         newCity.setCountry(this);      
      }
   }

   public void removeCity(City oldCity) {
      if (oldCity == null)
         return;
      if (this.cities != null && this.cities.contains(oldCity)) {
            this.cities.remove(oldCity);
            oldCity.setCountry((Country)null);
      }
   }

   public void removeAllCity() {
      if (cities != null)
      {
         City oldCity;
         for (Iterator<City> iter = getIteratorCity(); iter.hasNext();)
         {
            oldCity = iter.next();
            iter.remove();
            oldCity.setCountry((Country)null);
         }
      }
   }

}