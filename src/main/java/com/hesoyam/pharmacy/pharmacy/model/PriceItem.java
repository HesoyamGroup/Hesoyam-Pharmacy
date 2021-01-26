package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PriceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected double price;

    @Embedded
    protected DateTimeRange validThrough;

}