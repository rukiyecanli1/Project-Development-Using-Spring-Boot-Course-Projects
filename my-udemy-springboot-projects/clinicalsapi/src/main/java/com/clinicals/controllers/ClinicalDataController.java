package com.clinicals.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinicals.dto.ClinicalDataRequest;
import com.clinicals.entities.ClinicalData;
import com.clinicals.entities.Patient;
import com.clinicals.repos.ClinicalDataRepository;
import com.clinicals.repos.PatientRepository;
import com.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {
	
	@Autowired
	private ClinicalDataRepository clinicalDataRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	//when the user wants to add clinical data to a patient this method will be invoked
	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		Patient patient  = patientRepository.findById(request.getPatientId()).get();
	    ClinicalData clinicalData = new ClinicalData();
	    clinicalData.setComponentName(request.getComponentName());
	    clinicalData.setComponentValue(request.getComponentValue());
	    clinicalData.setPatient(patient);
	    return clinicalDataRepository.save(clinicalData);
	}
	
	//when the user wants to get clinical data according to patient id and component name
	//this method will be invoked and clinical data list will be returned 
	@RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId, @PathVariable("componentName")  String componentName){
		if(componentName.equals("bmi")){
			componentName= "hw";
		}
		List<ClinicalData> clinicalData = clinicalDataRepository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
		List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for (ClinicalData data : duplicateClinicalData) {
			BMICalculator.calculateBMI(clinicalData, data);
		}
		return clinicalData;
	}
	

}
