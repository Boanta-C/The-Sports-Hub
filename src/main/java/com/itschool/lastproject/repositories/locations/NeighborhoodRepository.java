package com.itschool.lastproject.repositories.locations;

import com.itschool.lastproject.entities.locations.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM neighborhood_sport WHERE neighborhood_id IN (?1)", nativeQuery = true)
    void deleteNeighborhoodById (Set<Long> ids);

    @Query(value = "SELECT neighborhood_id FROM neighborhood WHERE city_id = ?1", nativeQuery = true)
    Set<Long> selectNeighborhoodByCityId (Long id);

}
