package com.location.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.entities.Location;

//we say to Spring that the entity of our model class is Location and the private key type is Integer
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	//JPA or Hibernate will execute this query get the results
	//of group type and count of locations
	@Query(value = "select type,count(type) from location group by type", nativeQuery = true)
	public List<Object[]> findByRunId();
}
