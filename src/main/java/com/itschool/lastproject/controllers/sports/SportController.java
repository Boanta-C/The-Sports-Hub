package com.itschool.lastproject.controllers.sports;

import com.itschool.lastproject.entities.locations.City;
import com.itschool.lastproject.entities.locations.Neighborhood;
import com.itschool.lastproject.entities.sports.Sport;
import com.itschool.lastproject.entities.locations.SportFacility;
import com.itschool.lastproject.services.locations.CityService;
import com.itschool.lastproject.services.locations.NeighborhoodService;
import com.itschool.lastproject.services.locations.SportFacilityService;
import com.itschool.lastproject.services.sports.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sports")
public class SportController {

    @Autowired
    SportService sportService;

    @Autowired
    CityService cityService;

    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    SportFacilityService sportFacilityService;

    @GetMapping
    public String displayAllSports(Model model) {
        List<Sport> sports = sportService.findAll();
        model.addAttribute("sports", sports);
        return "project-entities/sports/all-sports";
    }

    @GetMapping("/add")
    public String displaySportForm(Model model) {

        Sport sport = new Sport();
        model.addAttribute("sport", sport);

        return "project-entities/sports/add-sport";
    }

    @GetMapping("/added")
    public String displaySportLocation(@RequestParam Long sportId, Model model) {
        List<City> cities = cityService.findAll();

        List<Neighborhood> neighborhoods = neighborhoodService.findAll();
//        List<Neighborhood> neighborhoods = cities.get(cities.indexOf(city)).getNeighborhoods();

        List<SportFacility> sportFacilities = sportFacilityService.findAll();

        model.addAttribute("allCities", cities);

        model.addAttribute("allNeighborhoods", neighborhoods);
        model.addAttribute("allFacilities", sportFacilities);
        model.addAttribute("sport", sportService.findBySportId(sportId));


        return "project-entities/sports/sport-location";
    }

    @PostMapping("/save")
    public String saveSport(Sport sport, RedirectAttributes redirectAttributes) {
        sportService.save(sport);
        redirectAttributes.addAttribute("sportId", sport.getSportId());
        return "redirect:/sports/added";
    }

    @PostMapping("/update")
    public String updateSport(Sport sport) {
        sportService.save(sport);
        return "redirect:/sports";
    }

    @PostMapping("/saveConnections")
    public String saveSportConnection(Sport sport) {
        sportService.save(sport);

        return "redirect:/sports";
    }

    @GetMapping("/delete/{id}")
    public String deleteSport(@PathVariable Long id) {
        sportService.deleteById(id);
        return "redirect:/sports";
    }

    //Just for owner and moderator
    @GetMapping("/update")
    public String updateSport(@RequestParam("sportId") Long id, Model model) {
        Sport sport = sportService.findById(id);
        List<Neighborhood> neighborhoods = neighborhoodService.findAll();
        List<City> cities = cityService.findAll();
        List<SportFacility> sportFacilities = sportFacilityService.findAll();

        model.addAttribute("neighborhoods", neighborhoods);
        model.addAttribute("sportFacilities", sportFacilities);
        model.addAttribute("cities", cities);
        model.addAttribute("sport", sport);
        return "/project-entities/sports/edit-sport";
    }

    @GetMapping("/selected/{id}")
    public String displaySport(Model model, @PathVariable Long id) {
        Sport sport = sportService.findById(id);
        model.addAttribute("sport", sport);
        return "project-entities/sports/selected-sport";
    }


}
