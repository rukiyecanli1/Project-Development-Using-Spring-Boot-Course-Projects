package com.dating.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dating.entities.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer>{

	//At runtime when this method is invoked, Spring data through Hibernate will execute this query, 
	//get the results and converts them into a list of users. It will return that back to our
	//controller which is UserAccountController
	@Query("from UserAccount where age=:ageComingFromController and city=:cityComing "
			+ "and country=:countryComing and id!=:idComing")
	List<UserAccount> findMatches(@Param("ageComingFromController") int age, 
			@Param("cityComing") String city,
			@Param("countryComing") String country,
			@Param("idComing") int id);
}
