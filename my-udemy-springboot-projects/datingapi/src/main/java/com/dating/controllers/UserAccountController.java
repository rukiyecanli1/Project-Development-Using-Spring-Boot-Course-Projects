package com.dating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dating.entities.UserAccount;
import com.dating.entities.Interest;
import com.dating.repos.UserAccountRepository;

import jakarta.validation.ConstraintViolationException;

import com.dating.repos.InterestRepository;

@RestController
@RequestMapping("/api")
public class UserAccountController {
	
	@Autowired
	private UserAccountRepository userRepo;
	
	@Autowired
	private InterestRepository interestRepo;

	//to register a new user
	//we are sending a user info as a JSON body in Postman app
	//url: localhost:8080/edating/api/users/register-user
	@PostMapping("/users/register-user")
	public UserAccount registerUser(@RequestBody UserAccount userAccount) {
		//if we get a validation error when we post a register, catch block will 
		//throw an exception to the client which is Postman in our case
		try {
			return userRepo.save(userAccount);	
		}catch(ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	
	}
	
	//to update a user interest
	@PostMapping("/interests/update")
	//we are sending user interests as a JSON body in Postman app
    //url: localhost:8083/edating/api/interests/update
	public Interest updateInterest(@RequestBody Interest interest) {
	//to find user, instead of using whole account info, we are using user id
	//to make our program more effective
	UserAccount userAccount = userRepo.findById(interest.getUserAccountId()).get();
	interest.setUserAccount(userAccount);
	return interestRepo.save(interest);
}
	//to get all users info
	//url: localhost:8083/edating/api/users/get/all
	@GetMapping("/users/get/all")
	public List<UserAccount> getUsers() {
		return userRepo.findAll();
	}
	
	//to delete interest
	//we are typing the id that we want to delete in the URL in Postman
	//url : localhost:8083/edating/api/users/delete/1
	@DeleteMapping("/users/delete/{interestId}")
	public void deleteInterest(@PathVariable("interestId") int id) {
		interestRepo.deleteById(id);
	}
	
	//to get all matches for a spesific user
	//we are typing the id that we want to match a user to the others in the URL in Postman
	//url : localhost:8083/edating/api/users/matches/4
	@GetMapping("/users/matches/{id}")
	public List<UserAccount> findMatches(@PathVariable("id") int id){
		UserAccount userAccount = userRepo.findById(id).get();
	    return userRepo.findMatches(userAccount.getAge(), userAccount.getCity(), 
	    		userAccount.getCountry(), id);
	}
}
