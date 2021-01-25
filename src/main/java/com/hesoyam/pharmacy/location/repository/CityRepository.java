package com.hesoyam.pharmacy.location.repository;

import com.hesoyam.pharmacy.location.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
    List<City> findAllByCountryIdOrderByCityName(Long countryId);
}
