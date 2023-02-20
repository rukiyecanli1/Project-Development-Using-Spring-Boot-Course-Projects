package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entities.User;

//we say to Spring that the entity of our model class is User and the id type is Long
public interface UserRepository extends JpaRepository<User,Long> {

// Thanks to Spring data, we need not write a single query here.
// Just follow this naming convention "findByEmail" and 
// this email is a field on the User. So automatically, 
// Spring data will generate a query for us, "select * 
// from the user table where email is equal to whatever 
// email" we are passing in in the request. That is the 
// beauty of Spring Data JPA. 
	User findByEmail(String email);

}
