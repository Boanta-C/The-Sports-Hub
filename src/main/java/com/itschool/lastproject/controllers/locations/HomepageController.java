package com.itschool.lastproject.controllers.locations;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.entities.sports.Sport;
import com.itschool.lastproject.services.locations.CityService;
import com.itschool.lastproject.services.locations.NeighborhoodService;
import com.itschool.lastproject.services.locations.SportFacilityService;
import com.itschool.lastproject.services.sports.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomepageController {

    @Autowired
    CityService cityService;

    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    SportFacilityService sportFacilityService;

    @Autowired
    SportService sportService;

    @GetMapping
    public String displayHomepage(Model model) {
        List<City> cities = cityService.findAll();
        List<Neighborhood> neighborhoods = neighborhoodService.findAll();
        List<SportFacility> sportFacilities = sportFacilityService.findAll();
        List<Sport> sports = sportService.findAll();
        model.addAttribute("cities", cities);
        model.addAttribute("neighborhoods", neighborhoods);
        model.addAttribute("sportFacilities", sportFacilities);
        model.addAttribute("sports", sports);
        return "home/homepage";
    }
}
