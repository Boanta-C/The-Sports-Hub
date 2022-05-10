package com.itschool.lastproject.controllers.locations;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.services.locations.CityService;
import com.itschool.lastproject.services.locations.NeighborhoodService;
import com.itschool.lastproject.services.locations.SportFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sportFacilities")
public class SportFacilityController {

    @Autowired
    SportFacilityService sportFacilityService;

    @Autowired
    CityService cityService;

    @Autowired
    NeighborhoodService neighborhoodService;

    @GetMapping
    public String displayAllSportFacilities (Model model) {
        List<SportFacility> sportFacilities = sportFacilityService.findAll();
        model.addAttribute("sportFacilities", sportFacilities);
        return "project-entities/sport-facilities/all-facilities";
    }

    @GetMapping("/add")
    public String displaySportFacilityForm (Model model) {

        SportFacility sportFacility = new SportFacility();
        List<City> cities = cityService.findAll();
        List<Neighborhood> neighborhoods = neighborhoodService.findAll();

        model.addAttribute("cities", cities);
        model.addAttribute("neighborhoods", neighborhoods);
        model.addAttribute("sportFacility", sportFacility);
        return "project-entities/sport-facilities/add-facility";
    }

    @PostMapping("/save")
    public String saveNeighborhood (SportFacility sportFacility, @RequestParam City city, @RequestParam Neighborhood neighborhood, Model model) {

        sportFacilityService.save(sportFacility);
        sportFacility.setCity(city);
        city.setCityNumberOfFacilities(city.getSportFacilities().size());
        cityService.save(city);
        sportFacility.setNeighborhood(neighborhood);
        neighborhood.setNeighborhoodNumberOfFacilities(neighborhood.getSportFacilities().size());
        neighborhoodService.save(neighborhood);
        return "redirect:/sportFacilities";
    }

    @GetMapping("/delete/{id}")
    public String deleteSportFacility(@PathVariable Long id) {
        SportFacility sportFacility = sportFacilityService.findById(id);
        sportFacility.getCity().setCityNumberOfFacilities
                (sportFacility.getCity().getCityNumberOfFacilities()-1);
        sportFacility.getNeighborhood().setNeighborhoodNumberOfFacilities(sportFacility.getNeighborhood().getNeighborhoodNumberOfFacilities()-1);
        sportFacilityService.deleteById(id);
        return "redirect:/neighborhoods";
    }

    //Just for owner and moderator
    @GetMapping("/update")
    public String updateSportFacility(@RequestParam("sportFacilityId") Long id, Model model) {

        SportFacility sportFacility = sportFacilityService.findById(id);
        List<City> cities = cityService.findAll();
        List<Neighborhood> neighborhoods = neighborhoodService.findAll();

        model.addAttribute("neighborhoods", neighborhoods);
        model.addAttribute("cities", cities);
        model.addAttribute("sportFacility", sportFacility);
        return "/project-entities/sport-facilities/edit-facility";
    }

}
