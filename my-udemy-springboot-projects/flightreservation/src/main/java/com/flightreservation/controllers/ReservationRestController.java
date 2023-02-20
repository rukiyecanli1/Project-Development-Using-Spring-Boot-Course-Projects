package com.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightreservation.dataTransferObject.ReservationUpdateRequest;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.ReservationRepository;
import com.flightreservation.services.ReservationServiceImpl;

@RestController
//We mark reservation test controller with cross origin so that 
//different applications running on different domains and ports
//can communicate with each other. (Angular uses port 4200)
@CrossOrigin  
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	ReservationRepository reservationRepo;
	
	//whatever reservation comes back from the database, that will be
	//serialized onto the wire as JSON and it will go back to the client
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: "+id );
		return reservationRepo.findById(id).get();
	}
	
	//When this "/reservations" request comes in(POST request), spring will 
	//desiarialize JSON(we type in POSTMAN) into this request object
	//
	//we are creating this "request" object because we don't want to pass
	//in the entire reservation to do this update method
	//The client need not pass in the entire reservation. 
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for : "+request );
		//we will fetch the reservation from db, update it, save it back to the db
		//and return the updatedReservation
		Reservation reservation = reservationRepo.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving reservation " );
		Reservation updatedReservation = reservationRepo.save(reservation);
		return updatedReservation;
		
	}
}
