package com.flightreservation.controllers;

import java.sql.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entities.Flight;
import com.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	
	//when the user login to the site he will see the find flights page.
	//He will fill the form and when he click the submit button(action="findFlights")
	//program will come to this controller and this method will work and show the 
	//displayFlights.jsp page to the user
	@RequestMapping(value = "/findFlights", method = RequestMethod.POST)
	public String findFlights(@RequestParam("from") String from, 
			@RequestParam("to") String to,  ModelMap modelMap) {
		//@RequestParam("departureDate") @DateTimeFormat(pattern="dd-MM-yyyy") Date departureDate,
		
//		System.out.println("Inside findFlights() From:" + from + " TO:" + to + "Departure Date: " + departureDate);
//		System.out.println(departureDate);  
//		System.out.println(TimeZone.getDefault().getID());  
		
		LOGGER.info("Inside findFlights(), from: "+from+"to: "+to);
		List<Flight> flights = flightRepo.findFlights(from,to);
		modelMap.addAttribute("flights", flights);
		LOGGER.info("flights found are: "+flights);
		return "displayFlights";
	}
	

}
