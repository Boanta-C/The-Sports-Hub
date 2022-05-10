package com.itschool.lastproject.repositories.sports;

import com.itschool.lastproject.entities.sports.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findBySportId(Long sportId);


}
