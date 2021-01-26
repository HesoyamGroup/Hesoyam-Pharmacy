package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ServicePriceItem extends PriceItem {
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

}
