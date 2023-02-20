package com.example.customer.dal.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.customer.dal.entities.Customer;

//we say to Spring that the entity of our model class is Customer and the id type is Integer
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
