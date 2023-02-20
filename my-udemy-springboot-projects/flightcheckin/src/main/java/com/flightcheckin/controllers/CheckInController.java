package com.flightcheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightcheckin.integration.ReservationRestClient;
import com.flightcheckin.integration.dataTransferObject.Reservation;
import com.flightcheckin.integration.dataTransferObject.ReservationUpdateRequest;

import jakarta.transaction.Transactional;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient restClient; 

	//When the user search for "http://localhost:8083/showStartCheckin" link
	//startCheckIn.jsp page will be displayed to him
	@RequestMapping("/showStartCheckin")
	public String showStartCheckin() {
		return "startCheckIn";
	}
	
	//the user will enter the reservation id and submit the form. When he click 
	//the submit button, this method will be invoked and then reservation will 
	//be found and sent to the displayReservationDetails.jsp to display all 
	//reservation info to the user and also number of bags will be taken by him
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") String reservationId, 
			ModelMap modelMap) {
		//String to long: 
		Long newReservationId=Long.parseLong(reservationId);
		Reservation reservation = restClient.findReservation(newReservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	//the user will enter the number of bag and submit the form. When he click the 
	//submit button, this method will be invoked. In this method, a ReservationUpdateRequest
	//object will be created and variables will be set. After then, success message will
	//be set to checkInConfirmation.jsp page to show to the user
	@RequestMapping("/completeCheckIn")
	//If there will be any problem when updateRequest is used, every
	// change will be rollbacked
	@Transactional
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
			@RequestParam("numberOfBags") int numberOfBags,  ModelMap modelMap) {  
		//We will send this object to post function as a body 
		ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		restClient.updateReservation(reservationUpdateRequest);
		modelMap.addAttribute("msg","Check in completed succesfully");
		return "checkInConfirmation";

	}
	
//We can find reservation with restClient.findReservation() method to set changes, 
//instead of creating a new ReservationUpdateRequest object.
//	@RequestMapping("/completeCheckIn")
//	public String completeCheckIn(@RequestParam("reservationId") String reservationId, 
//			@RequestParam("numberOfBags") String numberOfBags, ModelMap modelMap) {
//	        Long newReservationId = Long.parseLong(reservationId);
//			Integer newNumberOfBags = Integer.parseInt(numberOfBags);
//			Reservation reservation = restClient.findReservation(newReservationId);
//			reservation.setNumberOfBags(newNumberOfBags);
//			reservation.setCheckedIn(false);
//			System.out.println("burda");
//			modelMap.addAttribute("msg","Check in completed succesfully");
//			return "checkInConfirmation";
//	}
	
	}
