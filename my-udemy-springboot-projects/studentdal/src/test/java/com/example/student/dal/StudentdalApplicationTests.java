package com.example.student.dal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.student.dal.entities.Student;
import com.example.student.dal.repo.StudentRepository;

@SpringBootTest
class StudentdalApplicationTests {

	@Autowired
	private StudentRepository repo;
	
	@Test
	//Test of create operation in studenttab table in db
	void testCreateStudent() {
		Student student = new Student();
		student.setName("Jhon");
		student.setCourse("Spring Boot");
		student.setFee(30d);
		
		//student will be saved in studenttab table 
		//Hibernate: insert into studenttab (scourse, sfee, sname)
		//values (?, ?, ?)
		repo.save(student);
	}
	
	@Test
	void testFindStudentById() {
		//student will be find in studenttab table
		//Hibernate: select s1_0.id,s1_0.scourse,s1_0.sfee,s1_0.sname from studenttab s1_0 where s1_0.id=?
		Student student = repo.findById(1).get();
		System.out.println(student);
		//output: Student [id=1, name=Jhon, course=Spring Boot, fee=30.0]
	}
	
	@Test 
	void testUpdateStudent() {
		//student will be find and fee will be updated in studenttab table
		//Hibernate: select s1_0.id,s1_0.scourse,s1_0.sfee,s1_0.sname from studenttab s1_0 where s1_0.id=?
		//Hibernate: update studenttab set scourse=?, sfee=?, sname=? where id=?
		Student student = repo.findById(1).get();
		student.setFee(60d);
		repo.save(student);
	}
	
	@Test 
	void testDeleteStudent() {
		//Hibernate: select s1_0.id,s1_0.scourse,s1_0.sfee,s1_0.sname from studenttab s1_0 where s1_0.id=?
		//Hibernate: delete from studenttab where id=?
		Student student = repo.findById(3).get();
		repo.delete(student);
	}

}
