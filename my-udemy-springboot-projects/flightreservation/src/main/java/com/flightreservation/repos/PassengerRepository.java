package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entities.Passenger;

//we say to Spring that the entity of our model class is Passenger and the id type is Integer
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}
