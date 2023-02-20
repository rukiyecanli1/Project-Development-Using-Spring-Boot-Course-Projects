package com.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.dataTransferObject.ReservationRequest;
import com.flightreservation.entities.Flight;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	

	// When the request comes in with the following URI, this showCompleteReservation 
	// method will be invoked. We get the flightId, we are retrieving that information 
	// from the database, we are sending it again to the UI which is the 
	// completeReservation. We will create this JSP page in the next 
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, 
			ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() invoked with the flight id: "+flightId);
		Optional<Flight> flight=flightRepo.findById(flightId);
		modelMap.addAttribute("flight", flight.get());
		LOGGER.info("flight is: "+flight);
		return "completeReservation";
	}
	//When the user fill the form and click the submit button, this method will be invoked.
	//Here request object will be sent to the bookFlight method as a parameter to book a 
	//flight and then success message will be sent to reservationConfirmation.jsp page to 
	//show it to the end user
	@RequestMapping("/completeReservation")
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservation = reservationService.bookFlight(request);
		LOGGER.info("Inside completeReservation(), request: "+request);
		//with the id, customer can use it later on to check in to the flight
		modelMap.addAttribute("msg", "Reservation created succesfully and the id is "+ 
		reservation.getId());
		return "reservationConfirmation";
	}
}
