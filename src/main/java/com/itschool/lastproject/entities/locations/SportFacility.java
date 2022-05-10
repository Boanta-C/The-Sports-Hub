package com.itschool.lastproject.entities.locations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itschool.lastproject.entities.sports.Sport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class SportFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sportFacilityId;

    private String sportFacilityName;

    private String sportFacilityDescription;

    private String address;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openingHour;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closingHour;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")  // STERGE ASTA DACA CRAPA
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id")
    private Neighborhood neighborhood;

    //BETWEEN SPORTS FACILITIES AND SPORTS
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "sportFacility_sport",
            joinColumns = @JoinColumn(name = "sportFacility_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id"))
    @JsonIgnore
    private List<Sport> sports;

}