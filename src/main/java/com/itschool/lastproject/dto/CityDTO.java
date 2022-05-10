package com.itschool.lastproject.dto;

import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.entities.sports.Sport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {

    private Long cityId;
    private String cityName;

    private List<Neighborhood> neighborhoods;

    private List<SportFacility> sportFacilities;

    private List<Sport> sports;

    public CityDTO( String cityName, List<Neighborhood> neighborhoods, List<SportFacility> sportFacilities, List<Sport> sports) {
        this.cityName = cityName;
        this.neighborhoods = neighborhoods;
        this.sportFacilities = sportFacilities;
        this.sports = sports;
    }
}
