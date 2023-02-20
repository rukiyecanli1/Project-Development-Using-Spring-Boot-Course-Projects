package com.clinicals.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinicals.entities.ClinicalData;
import com.clinicals.entities.Patient;
import com.clinicals.repos.PatientRepository;
import com.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
//We need that annotationbecause our react js application will run on a different port(3000)
//and our Tomcat application the backend application will run on local host 8080.
//So these applications have to communicate with each other there will be a cross 
//origin issue. If we don't use this annotation here our server and spring will not 
//allow that communication to happen. So once we add this those two applications which 
//are running on different ports can communicate with each other.
//So when we make ajax calls from within our front end to the back end there will be no 
//issues although they are running on different ports they can even run on different 
//machines.
@CrossOrigin
public class PatientController {

	private PatientRepository repository;
	HashMap<String, String> filters = new HashMap<>();

	@Autowired
	PatientController(PatientRepository repository) {
		this.repository = repository;
	}

	// When the user or the client hits our restful API(localhost:8081/api)
	// with "/patients", this method will return list of patients.
	// RequestMethod.GETG method is default method
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients() {
		return repository.findAll();
	}

	// to display single patient to the user
	@RequestMapping(value = "/patients/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}

	// the json object that we typed in Postman will be saved here
	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public Patient savePatient(@RequestBody Patient patient) {
		return repository.save(patient);
	}

	@RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
	public Patient analyze(@PathVariable("id") int id) {
		Patient patient = repository.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for (ClinicalData data : duplicateClinicalData) {

			// to have only one of each component name in clinicalData list,
			// we are filtering it
			
			// in the beginning filters map is empty and so the first component 
			// will be put in filters map
			if (filters.containsKey(data.getComponentName())) {
				clinicalData.remove(data);
				continue;
			} else {
				filters.put(data.getComponentName(), null);
			}

			BMICalculator.calculateBMI(clinicalData, data);
			
		}
		filters.clear();
		return patient;
	}


}
