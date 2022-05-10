package com.itschool.lastproject.mappers;

import com.itschool.lastproject.dto.CityDTO;
import com.itschool.lastproject.entities.locations.City;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    public CityDTO cityEntityToDto(City city) {

        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityId(city.getCityId());
        cityDTO.setCityName(city.getCityName());
        cityDTO.setNeighborhoods(city.getNeighborhoods());
        cityDTO.setSportFacilities(city.getSportFacilities());
        cityDTO.setSports(city.getSports());

        return cityDTO;
    }

    public List<CityDTO> citiesListToDtoList (List<City> cities) {
        return cities.stream().map(this::cityEntityToDto).collect(Collectors.toList());
    }

    public City cityDtoToEntity (CityDTO cityDTO) {
        City city = new City();
        city.setCityName(cityDTO.getCityName());
        city.setNeighborhoods(cityDTO.getNeighborhoods());
        city.setSportFacilities(cityDTO.getSportFacilities());
        city.setSports(cityDTO.getSports());

        return city;
    }

    public List<City> citiesDtoToList (List<CityDTO> cityDTOS) {
        return cityDTOS.stream().map(this::cityDtoToEntity).collect(Collectors.toList());
    }

}
