package com.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.location.entities.Location;
import com.location.repo.LocationRepository;

//We used Spring MVC architect in LocationController.class
//Here, we will use Spring REST architect 
//Thanks to REST, we don't need to use any template engines
//(JSP, HTML..) anymore!
//We will see return object in website without sending them
//template engines

//Rest makes our job easier!

//https://medium.com/@kemalyen/controller-ve-restcontroller-
//annotasyonlar%C4%B1-d9c7224b68a0

//If there are Controller and RestController class in our 
//controllers package, Spring will automatically work with 
//RestController class

@RestController
@RequestMapping("/locations")
public class LocationRestController {

	@Autowired
	LocationRepository locationRepository;
	
	@GetMapping
	public List<Location> getLocations(){
		return locationRepository.findAll();
	}
	
	@PostMapping
	public Location createLocation(@RequestBody Location location) {
		return locationRepository.save(location);
	}
	
	@PutMapping
	public Location updateLocation(@RequestBody Location location) {
		return locationRepository.save(location);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		locationRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable("id") int id) {
		return locationRepository.findById(id).get();
	}
}
