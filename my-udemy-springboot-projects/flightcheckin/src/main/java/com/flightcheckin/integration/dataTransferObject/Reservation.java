package com.flightcheckin.integration.dataTransferObject;



public class Reservation {
	
	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;
	
	//Every reservation will have a passenger and a flight.
	//When we save a reservation, a passenger and flight information
	//should be saved.
	//These two foreign keys will be saved in the resrevation table
	
	private Passenger passenger;
	
	private Flight flight;
	
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
