package com.itschool.lastproject.services.locations;

import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.repositories.locations.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NeighborhoodService {

    @Autowired
    NeighborhoodRepository neighborhoodRepository;
    public List<Neighborhood> findAll() {
        return neighborhoodRepository.findAll();
    }

    public void save(Neighborhood neighborhood) {
        neighborhoodRepository.save(neighborhood);
    }

    public void deleteNeighborhoodById(Set<Long> ids) {
        neighborhoodRepository.deleteNeighborhoodById(ids);
    }

    public void deleteById(Long id) {
        neighborhoodRepository.deleteById(id);
    }

    public Set<Long> selectNeighborhoodByCityId (Long id) {
        return neighborhoodRepository.selectNeighborhoodByCityId(id);
    }

    public Neighborhood findById(Long id) {
        return neighborhoodRepository.getById(id);
    }
}
