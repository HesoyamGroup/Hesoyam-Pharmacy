package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PriceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    @Min(0)
    protected double price;

    @Embedded
    protected DateTimeRange validThrough;

}