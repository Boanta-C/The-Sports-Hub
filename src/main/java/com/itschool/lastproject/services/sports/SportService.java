package com.itschool.lastproject.services.sports;

import com.itschool.lastproject.entities.sports.Sport;
import com.itschool.lastproject.repositories.sports.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {

    @Autowired
    SportRepository sportRepository;

    public List<Sport> findAll() {
        return sportRepository.findAll();
    }

//    public List<String> sportsName() {
//        return sportRepository.findAll().stream().map(Sport::getSportName).toList();
//    }

    public Sport save(Sport sport) {
        return sportRepository.save(sport);
    }

    public Sport findBySportId(Long sportId) {
        return sportRepository.findBySportId(sportId);
    }

    public Sport findById(Long sportId) {
        return sportRepository.findBySportId(sportId);
    }

    public void deleteById(Long id) {
        sportRepository.deleteById(id);
    }
}
