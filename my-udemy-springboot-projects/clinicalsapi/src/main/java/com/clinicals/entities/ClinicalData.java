package com.clinicals.entities;                                                                                                                                                                                
                                                                                                                                                                                                               
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;                                                                                                                                                                                  
                                                                                                                                                                                                               
@Entity  
//if table name was "clinical_data" in db,
//we wouldn't need to use @table annotation
@Table(name="clinicaldata")
public class ClinicalData {                                                                                                                                                                                    
                                                                                                                                                                                                               
	@Id          
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;                                                                                                                                                                                            
	private String componentName;                                                                                                                                                                              
	private String componentValue;                                                                                                                                                                             
	private Timestamp measuredDateTime;                                                                                                                                                                         
    
	@ManyToOne(fetch=FetchType.LAZY)
	//"nullable=false" means it will throw some exceptions if we try 
	//to inject clinical data without the patient id
	@JoinColumn(name="patient_id", nullable=false)
	@JsonIgnore
	private Patient patient;                                                                                                                                                                                   
	                                                                                                                                                                                                           
	public int getId() {                                                                                                                                                                                       
		return id;                                                                                                                                                                                             
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public void setId(int id) {                                                                                                                                                                                
		this.id = id;                                                                                                                                                                                          
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public String getComponentName() {                                                                                                                                                                         
		return componentName;                                                                                                                                                                                  
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public void setComponentName(String componentName) {                                                                                                                                                       
		this.componentName = componentName;                                                                                                                                                                    
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public String getComponentValue() {                                                                                                                                                                        
		return componentValue;                                                                                                                                                                                 
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public void setComponentValue(String componentValue) {                                                                                                                                                     
		this.componentValue = componentValue;                                                                                                                                                                  
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public Timestamp getMeasuredDateTime() {                                                                                                                                                                    
		return measuredDateTime;                                                                                                                                                                                
	}                                                                                                                                                                                                          
                                                                                                                                                                                                               
	public void setMeasuredDateTime(Timestamp measuredDateTime) {                                                                                                                                                
		this.measuredDateTime = measuredDateTime;                                                                                                                                                                
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}                                                                                                                                                                                                          
}                                                                                                                                                                                                              
                                                                                                                                                                                                               