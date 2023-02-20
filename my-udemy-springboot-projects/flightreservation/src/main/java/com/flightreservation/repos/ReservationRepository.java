package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entities.Reservation;

//we say to Spring that the entity of our model class is Reservation and the id type is Long
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
