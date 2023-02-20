package com.vendor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.entities.Vendor;
import com.vendor.repo.VendorRepository;


//We used Spring MVC architect in LocationController.class
//Here, we will use Spring REST architect 
//Thanks to REST, we don't need to use any template engines
//(JSP, HTML..) anymore!
//We will see return object in web site without sending them
//template engines

//Unlike @Controller notation, @RestController can directly                                            
//present the data itself in JSON or XML format.

//Rest makes our job easier!

//https://medium.com/@kemalyen/controller-ve-restcontroller-
//annotasyonlar%C4%B1-d9c7224b68a0

//If there are Controller and RestController class in our 
//controllers package, Spring will automatically work with 
//RestController class

@RestController
@RequestMapping("/vendors")
public class VendorRestController {

	@Autowired
	VendorRepository vendorRepository;
	
	@GetMapping
	//in Postman: localhost:8081/vendorweb/vendors
	public List<Vendor> getLocations(){
		return vendorRepository.findAll();
	}
	
	@PostMapping
	//in Postman: localhost:8081/vendorweb/vendors
	//and we type a JSON type body there
	public Vendor createLocation(@RequestBody Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	@PutMapping
	//in Postman: localhost:8081/vendorweb/vendors
	//and we type a JSON type body there
	public Vendor updateVendor(@RequestBody Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	@DeleteMapping("/{id}")
	//in Postman: localhost:8081/vendorweb/vendors/1
	public void deleteVendor(@PathVariable("id") int id) {
		vendorRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	//in Postman: localhost:8081/vendorweb/vendors/1
	public Vendor getVendor(@PathVariable("id") int id) {
		return vendorRepository.findById(id).get();
	}
}
