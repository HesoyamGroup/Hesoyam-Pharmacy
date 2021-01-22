/***********************************************************************
 * Module:  Country.java
 * Author:  Geri
 * Purpose: Defines the Class Country
 ***********************************************************************/
package com.hesoyam.pharmacy.location.model;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Country {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(unique = true, length = 3)
   private String countryCode;
   @Column(length = 100)
   private String countryName;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "country")
   private List<City> cities;

   public List<City> getCities() {
      if (cities == null)
         cities = new ArrayList<City>();
      return cities;
   }

   public Iterator getIteratorCity() {
      if (cities == null)
         cities = new ArrayList<City>();
      return cities.iterator();
   }

   public void setCities(List<City> newCity) {
      removeAllCity();
      for (Iterator iter = newCity.iterator(); iter.hasNext();)
         addCity((City)iter.next());
   }

   public void addCity(City newCity) {
      if (newCity == null)
         return;
      if (this.cities == null)
         this.cities = new ArrayList<City>();
      if (!this.cities.contains(newCity))
      {
         this.cities.add(newCity);
         newCity.setCountry(this);      
      }
   }

   public void removeCity(City oldCity) {
      if (oldCity == null)
         return;
      if (this.cities != null)
         if (this.cities.contains(oldCity))
         {
            this.cities.remove(oldCity);
            oldCity.setCountry((Country)null);
         }
   }

   public void removeAllCity() {
      if (cities != null)
      {
         City oldCity;
         for (Iterator iter = getIteratorCity(); iter.hasNext();)
         {
            oldCity = (City)iter.next();
            iter.remove();
            oldCity.setCountry((Country)null);
         }
      }
   }

}