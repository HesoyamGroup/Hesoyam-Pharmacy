package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
public class ServicePrice {
    @Column
    @Min(0)
    private double checkUpPrice;

    @Column
    @Min(0)
    private double counselingPrice;
}
