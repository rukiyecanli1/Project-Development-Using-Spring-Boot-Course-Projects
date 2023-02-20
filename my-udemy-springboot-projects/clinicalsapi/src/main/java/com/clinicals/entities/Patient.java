package com.clinicals.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String age;
	
	//OneToMany means patient can have multiple records in the 
	//clinical data table.
	//I'm using "All" that means whatever is done on the patient 
	//table all that will be impacted on the clinical data as well.
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="patient")
    private List<ClinicalData> clinicalData;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<ClinicalData> getClinicalData() {
		return clinicalData;
	}

	public void setClinicalData(List<ClinicalData> clinicalData) {
		this.clinicalData = clinicalData;
	}

}
