package com.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.location.entities.Location;

//we say to Spring that the entity of our model class is Location and the private key type is Integer
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
