package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ServicePrice {
    @Column
    private double checkUpPrice;

    @Column
    private double counselingPrice;
}
