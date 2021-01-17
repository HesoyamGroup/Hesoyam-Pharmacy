/***********************************************************************
 * Module:  ServicePriceListItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class ServicePriceListItem
 ***********************************************************************/
package com.hesoyam.pharmacy.finance.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ServicePriceListItem extends PriceListItem {

   @Enumerated(EnumType.STRING)
   private ServiceType serviceType;

}