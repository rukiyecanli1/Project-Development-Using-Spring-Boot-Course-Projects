package com.flightreservation.services;

import com.flightreservation.dataTransferObject.ReservationRequest;
import com.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
	
}
