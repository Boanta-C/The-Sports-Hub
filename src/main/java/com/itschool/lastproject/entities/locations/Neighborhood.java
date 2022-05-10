package com.itschool.lastproject.entities.locations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itschool.lastproject.entities.sports.Sport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long neighborhoodId;

    private String neighborhoodName;

    private int neighborhoodNumberOfFacilities;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    //BETWEEN NEIGHBORHOOD AND SPORT FACILITIES
    @OneToMany(mappedBy = "neighborhood",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SportFacility> sportFacilities;

    //BETWEEN NEIGHBORHOOD AND SPORTS
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "neighborhood_sport",
            joinColumns = @JoinColumn(name = "neighborhood_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id"))
    @JsonIgnore
    private List<Sport> sports;
}
