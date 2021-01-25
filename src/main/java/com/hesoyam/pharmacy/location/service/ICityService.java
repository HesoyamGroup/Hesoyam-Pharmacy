package com.hesoyam.pharmacy.location.service;

import com.hesoyam.pharmacy.location.model.City;

import java.util.List;

public interface ICityService {
    City getById(Long id);
    City getByName(String name);
    List<City> getAllCitiesInCountry(Long id);
}
