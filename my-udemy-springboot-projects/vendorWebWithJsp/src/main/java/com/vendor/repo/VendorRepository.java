package com.vendor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.vendor.entities.Vendor;

//we say to Spring that the entity of our model class is Vendor and the 
//private key type is Integer
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}




