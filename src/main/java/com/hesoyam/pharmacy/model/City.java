/***********************************************************************
 * Module:  City.java
 * Author:  Geri
 * Purpose: Defines the Class City
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class City {
   private Long cityId;
   private String cityName;
   
   private Country country;
   
   
   /** @pdGenerated default parent getter */
   public Country getCountry() {
      return country;
   }
   
   /** @pdGenerated default parent setter
     * @param newCountry */
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