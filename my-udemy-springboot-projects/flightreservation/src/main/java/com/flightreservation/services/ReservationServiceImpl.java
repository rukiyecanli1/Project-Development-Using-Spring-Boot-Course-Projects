package com.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.flightreservation.dataTransferObject.ReservationRequest;
import com.flightreservation.entities.Flight;
import com.flightreservation.entities.Passenger;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.repos.PassengerRepository;
import com.flightreservation.repos.ReservationRepository;
import com.flightreservation.util.EmailUtil;
import com.flightreservation.util.PDFGenerator;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

	// Spring will inject that value from application.properties into the
	// ITINERARY_DIR at runtime
	@Value("${com.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepo;

	@Autowired
	PassengerRepository passengerRepo;

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	// Once we type @Transactional, Spring will automatically create a transaction
	// at runtime. And if an exception is thrown from any of these methods here or any
	// of these tasks that are happening here, the entire transaction will be rolled
	// back and nothing will be saved to the database, no email will go out.
	//
	// This method takes the request object from controller,
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");

		// Make Payment

		// retrieves the flight from database,
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for flight id: " + flightId);
		Flight flight = flightRepo.findById(flightId).get();

		// creates a new passenger on the database,
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger: " + passenger);
		Passenger savedPassenger = passengerRepo.save(passenger);

		// uses that info to create ad save the reservation
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the reservation: " + reservation);
		Reservation savedReservation = reservationRepo.save(reservation);

		// pdf will be sent to the user email
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Sending the itinerary to the email");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		// at the end of it, it returns that reservation
		// back to the controller
		return savedReservation;
	}

}
