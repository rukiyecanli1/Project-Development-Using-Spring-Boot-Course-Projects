package com.flightreservation.dataTransferObject;

//to understand what "data transfer object" is: https://www.youtube.com/watch?v=F9M9bUq-0Z0

public class ReservationUpdateRequest {

	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
}
