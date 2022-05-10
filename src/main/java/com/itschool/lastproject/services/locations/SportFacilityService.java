package com.itschool.lastproject.services.locations;

import com.itschool.lastproject.repositories.locations.SportFacilityRepository;
import com.itschool.lastproject.entities.locations.SportFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportFacilityService {

    @Autowired
    SportFacilityRepository sportFacilityRepository;
    public List<SportFacility> findAll() {
        return sportFacilityRepository.findAll();
    }

    public void save(SportFacility sportFacility) {
        sportFacilityRepository.save(sportFacility);
    }

    public void deleteById(Long id) {
        sportFacilityRepository.deleteById(id);
    }

    public SportFacility findById(Long id) {
        return  sportFacilityRepository.getById(id);
    }
}
