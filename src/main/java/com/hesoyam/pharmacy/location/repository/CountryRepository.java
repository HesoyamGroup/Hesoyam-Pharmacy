package com.hesoyam.pharmacy.location.repository;

import com.hesoyam.pharmacy.location.model.City;
import com.hesoyam.pharmacy.location.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
    List<Country> findAllByOrderByCountryNameAsc();
}
