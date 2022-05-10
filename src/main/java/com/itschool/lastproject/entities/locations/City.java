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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cityId;

    @Column(unique = true)
    private String cityName;

    private int cityNumberOfFacilities;

    //BETWEEN CITY AND NEIGHBORHOODS
    @OneToMany(mappedBy = "city",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Neighborhood> neighborhoods;

    //BETWEEN CITY ANDSPORT FACILITIES
    @OneToMany(mappedBy = "city",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SportFacility> sportFacilities;

    //BETWEEN CITY AND SPORTS
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "city_sport",
    joinColumns = @JoinColumn(name = "city_id"),
    inverseJoinColumns = @JoinColumn(name = "sport_id"))
    @JsonIgnore
    private List<Sport> sports;
}
