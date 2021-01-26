package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.model.Manufacturer;

import java.util.List;

public interface IManufacturerService {
    List<Manufacturer> getAll();
    Manufacturer create(Manufacturer manufacturer);
}
