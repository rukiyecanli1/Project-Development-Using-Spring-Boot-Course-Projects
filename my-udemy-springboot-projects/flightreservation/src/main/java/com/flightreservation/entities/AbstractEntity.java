package com.flightreservation.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

//Any common fields across our entities will go into this AbstactEntity

//MappedSuperclass tells that this class is not directly mapped to
//a database table instead it acts as a parent class for all the
//other entities
@MappedSuperclass
public class AbstractEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
