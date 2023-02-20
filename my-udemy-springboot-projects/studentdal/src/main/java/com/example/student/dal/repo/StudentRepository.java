package com.example.student.dal.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.student.dal.entities.Student;

//we say to Spring that the entity of our model class is Student and the id type is Integer
public interface StudentRepository extends CrudRepository<Student, Integer > {

}
