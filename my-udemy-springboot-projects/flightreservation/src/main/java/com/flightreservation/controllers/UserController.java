package com.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entities.User;
import com.flightreservation.repos.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	 
	 //this will show the registration page to the end user
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	//When user submit the form from the registerUser page, it 
	//will come to this Controller method.
	//This method takes the user from registration page, saves it 
	//to the database and then it returns the view as well
	@RequestMapping(value="/regUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside {} register()"+user);
		//to store password safely in database
		userRepo.save(user);
		return "login/login";
	}
	
	//this will show the login page to the end user, if he clicks 
	//the login link
		@RequestMapping("/showLogin")
		public String showLoginPage() {
			LOGGER.info("Inside  showLoginPage()");
			return "login/login";
		}
	
	//we use requestMethos.POST because in the login page
	//we are using a post method to submit the form
	
	//we use requestParam to get parameters from login page
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, 
			@RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.info("Inside login() and the email is: "+email);
		User user = userRepo.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}
		else {
			modelMap.addAttribute("msg","Invalid user name or password. "
					+ "Please try again!");	
		}
		return "login/login";
	}
}
