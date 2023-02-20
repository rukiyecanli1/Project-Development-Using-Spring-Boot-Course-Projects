package com.document.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.document.entities.Document;

//we say to Spring that the entity of our model class is Document and the private key type is Long
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
