package com.hesoyam.pharmacy.location.service;

import com.hesoyam.pharmacy.location.model.Country;

import java.util.List;

public interface ICountryService {
    Country findById(Long id);
    Country findByName(String countryName);
    List<Country> getAll();
}
