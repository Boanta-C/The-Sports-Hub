package com.itschool.lastproject.services.locations;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.repositories.locations.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    public City findById(Long id) {
        return cityRepository.getById(id);
    }
}
