/***********************************************************************
 * Module:  Country.java
 * Author:  Geri
 * Purpose: Defines the Class Country
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Country {
   private Long countryId;
   private String countryCode;
   private String countryName;
   
   private java.util.List<City> city;
   
   
   /** @pdGenerated default getter */
   public java.util.List<City> getCity() {
      if (city == null)
         city = new java.util.ArrayList<City>();
      return city;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorCity() {
      if (city == null)
         city = new java.util.ArrayList<City>();
      return city.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newCity */
   public void setCity(java.util.List<City> newCity) {
      removeAllCity();
      for (java.util.Iterator iter = newCity.iterator(); iter.hasNext();)
         addCity((City)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCity */
   public void addCity(City newCity) {
      if (newCity == null)
         return;
      if (this.city == null)
         this.city = new java.util.ArrayList<City>();
      if (!this.city.contains(newCity))
      {
         this.city.add(newCity);
         newCity.setCountry(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldCity */
   public void removeCity(City oldCity) {
      if (oldCity == null)
         return;
      if (this.city != null)
         if (this.city.contains(oldCity))
         {
            this.city.remove(oldCity);
            oldCity.setCountry((Country)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllCity() {
      if (city != null)
      {
         City oldCity;
         for (java.util.Iterator iter = getIteratorCity(); iter.hasNext();)
         {
            oldCity = (City)iter.next();
            iter.remove();
            oldCity.setCountry((Country)null);
         }
      }
   }

}