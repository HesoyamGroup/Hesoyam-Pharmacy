package com.hesoyam.pharmacy.location.service.impl;

import com.hesoyam.pharmacy.location.model.City;
import com.hesoyam.pharmacy.location.repository.CityRepository;
import com.hesoyam.pharmacy.location.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByCityName(name);
    }

    @Override
    public List<City> getAllCitiesInCountry(Long id) {
        return cityRepository.findAllByCountryIdOrderByCityName(id);
    }


}
