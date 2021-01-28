package com.hesoyam.pharmacy.location.service.impl;

import com.hesoyam.pharmacy.location.model.Country;
import com.hesoyam.pharmacy.location.repository.CountryRepository;
import com.hesoyam.pharmacy.location.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
    }

    @Override
    public Country findByName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAllByOrderByCountryNameAsc();
    }
}
