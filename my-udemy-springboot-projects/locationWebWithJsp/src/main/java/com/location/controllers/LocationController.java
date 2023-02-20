package com.location.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.location.entities.Location;
import com.location.service.LocationService;
import java.util.List;

@Controller
public class LocationController {

	@Autowired
	LocationService service;

	// this method returns a string which is a JSP page to display to the end user
	// if the user hits this particular URL("../showCreate") through our
	// application, a page which is createLocation.jsp will be sent to him.
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation"; // the JSP page name
	}

	// if the user clicks "save" button through our application, this uri will open
	// and then locationwill be saven in db and success message will be sent to
	// the createLocation.jsp to show it to the user
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = service.saveLocation(location);
		// Hibernate: select l1_0.id,l1_0.code,l1_0.name,l1_0.type from location l1_0
		// where l1_0.id=?
		// Hibernate: update location set code=?, name=?, type=? where id=?

		String successMsg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("successMsg", successMsg);
		return "createLocation";
	}

	// if the user clicks "view all" button through our application, this uri will
	// open and then locations will be sent to displayLocation.jsp to show them as a
	// table to the user
	@RequestMapping("/displayLocs")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	// if the user clicks "delete" button through our application, this uri will
	// open and then choosen location will be deleted and new locations list will be sent
	// to displayLocation.jsp to show them as a new table to the user
	@RequestMapping("/deleteLoc")
	public String deleteLocation(@RequestParam("id") int locId, ModelMap modelMap) {
		// Location location = service.getLocationById(locId);
		Location location = new Location();
		location.setId(locId);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	// if the user clicks "edit" button through our application, this uri will
	// open and then the choosen one will be added a model and edit page wil be open
	@RequestMapping("/showEdit")
	public String showEdit(@RequestParam("id") int locId, ModelMap modelMap) {
		Location location = service.getLocationById(locId);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}
    
	// when the user fill the form again and save the button, this uri will open
	// and updated locations will be sent to displayLocations page to show it to user
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

}
