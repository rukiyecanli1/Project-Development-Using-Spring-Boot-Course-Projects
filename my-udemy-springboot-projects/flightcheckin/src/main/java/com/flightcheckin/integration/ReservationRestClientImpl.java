package com.flightcheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.flightcheckin.integration.dataTransferObject.Reservation;
import com.flightcheckin.integration.dataTransferObject.ReservationUpdateRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	//We are integrating flightreservation app and flighcheckin app
	

	//Spring will inject that value from application.properties 
	//into the RESERVATION_REST_URL at runtime
	@Value("${com.flightcheckin.reservation.rest.url}")
	private String RESERVATION_REST_URL ;

	@Override
	public Reservation findReservation(Long id) {
		//GET Mapping
		RestTemplate restTemplate = new RestTemplate();
		//We get id from CheckinController class and we find reservation object
		Reservation reservation = restTemplate.getForObject(RESERVATION_REST_URL+"/"+id, 
				Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest request) {
		//POST Mapping
		RestTemplate restTemplate = new RestTemplate();
	    //We get request object from CheckinController class and we update reservation object
		Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL, request, Reservation.class);
		return reservation;
	}

}
