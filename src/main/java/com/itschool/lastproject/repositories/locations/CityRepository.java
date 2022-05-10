package com.itschool.lastproject.repositories.locations;

import com.itschool.lastproject.entities.locations.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
