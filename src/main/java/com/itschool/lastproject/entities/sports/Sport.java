package com.itschool.lastproject.entities.sports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.locations.SportFacility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sportId;

    private String sportName;

    private String locationType; // OUTDOOR - INDOOR

    private String priceCategory; //FREE - PAID

    private Integer price;

    //BETWEEN SPORTS AND CITIES
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "city_sport",
    joinColumns = @JoinColumn(name = "sport_id"),
    inverseJoinColumns = @JoinColumn(name = "city_id"))
    private List<City> cities;

    //BETWEEN SPORTS AND NEIGHBORHOODS
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "neighborhood_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "neighborhood_id"))
    @JsonIgnore
    private List<Neighborhood> neighborhoods;


    //BETWEEN SPORTS AND SPORTS FACILITIES
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "sportFacility_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "sportFacility_id"))
    @JsonIgnore
    private List<SportFacility> sportFacilities;

}
