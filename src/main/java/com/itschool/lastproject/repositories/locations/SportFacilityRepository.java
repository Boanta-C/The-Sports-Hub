package com.itschool.lastproject.repositories.locations;

import com.itschool.lastproject.entities.locations.SportFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportFacilityRepository extends JpaRepository<SportFacility, Long> {
}
