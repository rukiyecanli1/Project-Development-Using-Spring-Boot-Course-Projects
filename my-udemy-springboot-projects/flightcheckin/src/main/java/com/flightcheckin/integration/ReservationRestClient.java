package com.flightcheckin.integration;

import com.flightcheckin.integration.dataTransferObject.Reservation;
import com.flightcheckin.integration.dataTransferObject.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);
}
