package com.itschool.lastproject.controllers.locations;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.services.locations.CityService;
import com.itschool.lastproject.services.locations.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("neighborhoods")
public class NeighborhoodController {

    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    CityService cityService;

    @GetMapping
    public String displayAllNeighborhoods (Model model) {
        List<Neighborhood> neighborhoods = neighborhoodService.findAll();
        model.addAttribute("neighborhoods", neighborhoods);
        return "project-entities/neighborhoods/all-neighborhoods";
    }

    @GetMapping("/add")
    public String displayNeighborhoodForm (Model model) {
        Neighborhood neighborhood = new Neighborhood();

        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);

        model.addAttribute("neighborhood", neighborhood);
        return "project-entities/neighborhoods/add-neighborhood";
    }

    @PostMapping("/save")
    public String saveNeighborhood (Neighborhood neighborhood, @RequestParam City city) {
        neighborhoodService.save(neighborhood);
        neighborhood.setCity(city);
        return "redirect:/neighborhoods";
    }

    @GetMapping("/delete/{id}")
    public String deleteNeighborhood(@PathVariable Long id) {
        neighborhoodService.deleteById(id);
        return "redirect:/neighborhoods";
    }

    //Just for owner and moderator
    @GetMapping("/update")
    public String updateNeighborhood(@RequestParam("neighborhoodId") Long id, Model model) {
        Neighborhood neighborhood = neighborhoodService.findById(id);
        List<SportFacility> sportFacilities = neighborhood.getSportFacilities();
        List<City> cities = cityService.findAll();
        model.addAttribute("sportFacilities", sportFacilities);
        model.addAttribute("cities", cities);
        model.addAttribute("neighborhood", neighborhood);
        return "/project-entities/neighborhoods/edit-neighborhood";
    }

}
