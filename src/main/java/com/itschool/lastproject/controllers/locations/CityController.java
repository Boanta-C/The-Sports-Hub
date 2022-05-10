package com.itschool.lastproject.controllers.locations;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.mappers.CityMapper;
import com.itschool.lastproject.services.locations.CityService;
import com.itschool.lastproject.services.locations.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    CityMapper cityMapper;

    @GetMapping
    public String displayAllCities (Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);


        return "project-entities/cities/all-cities";
    }

    @GetMapping("/add")
    public String displayCityForm (Model model) {
        City city = new City();
        model.addAttribute("city", city);
        return "project-entities/cities/add-city";
    }

    @PostMapping("/save")
    public String saveCity (City city, Model model) {
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        neighborhoodService.deleteNeighborhoodById(neighborhoodService.selectNeighborhoodByCityId(id));
        cityService.deleteById(id);
        return "redirect:/cities";
    }

    //Just for owner and moderator
    @GetMapping("/update")
    public String updateCity(@RequestParam("cityId") Long id, Model model) {
        City city = cityService.findById(id);
        List<SportFacility> sportFacilities = city.getSportFacilities();
        model.addAttribute("sportFacilities", sportFacilities);
        model.addAttribute("city", city);
        return "/project-entities/cities/edit-city";
    }
}
