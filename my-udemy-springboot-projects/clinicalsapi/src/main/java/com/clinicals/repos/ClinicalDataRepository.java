package com.clinicals.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicals.entities.ClinicalData;

//we say to Spring that the entity of our model class is ClinicalData and the id type is int
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	 List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
