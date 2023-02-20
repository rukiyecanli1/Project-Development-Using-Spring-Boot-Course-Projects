package com.example.customer.dal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.customer.dal.entities.Customer;
import com.example.customer.dal.repo.CustomerRepository;


@SpringBootTest
class CustomerdalApplicationTests {

	@Autowired
	private CustomerRepository repo;

	@Test // Test of create operation in customertab table in db
	void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("Bryan");
		customer.setEmail("brian@gmail.com");
		// customer will be saved in customertab table
		// Hibernate: insert into customertab (email, name) values (?, ?)
		repo.save(customer);
	}

	@Test void testFindCustomerById() { 
	  //student will be find in customertab table 
	  //Hibernate: select c1_0.id,c1_0.email,c1_0.name from customertab c1_0 where c1_0.id=? 
	 Customer customer = repo.findById(1).get();
	 System.out.println(customer);
	}
	
	@Test
	void testUpdateCustomer() {
		// customer will be find and email will be updated in customertab table
		// Hibernate: select c1_0.id,c1_0.email,c1_0.name from customertab c1_0 where
		// c1_0.id=?
		// Hibernate: update customertab set email=?, name=? where id=?
		Customer customer = repo.findById(1).get();
		customer.setEmail("brian.de@gmail.com");
		repo.save(customer);
	}
	
	@Test 
	void testDeleteCustomer() {
		//Hibernate: select c1_0.id,c1_0.email,c1_0.name from customertab c1_0 where c1_0.id=?
		//Hibernate: delete from customertab where id=?
		Customer customer = repo.findById(1).get();
		repo.delete(customer);
	}

}