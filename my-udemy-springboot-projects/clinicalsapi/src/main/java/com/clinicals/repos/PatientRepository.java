package com.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicals.entities.Patient;

//we say to Spring that the entity of our model class is Patient and the id type is int
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
