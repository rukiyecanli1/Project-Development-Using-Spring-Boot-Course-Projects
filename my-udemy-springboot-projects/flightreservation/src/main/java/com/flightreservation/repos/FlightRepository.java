package com.flightreservation.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightreservation.entities.Flight;

//we say to Spring that the entity of our model class is Flight and the id type is Long
public interface FlightRepository extends JpaRepository<Flight,Long> {

	//At runtime when this method is invoked, Spring data through Hibernate will execute this query, 
	//get the results and converts them into a list of flights. It will return that back to our
	//controller which is FlightController
	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to);
	//@Param("dateOfDeparture") Date departureDate
}

