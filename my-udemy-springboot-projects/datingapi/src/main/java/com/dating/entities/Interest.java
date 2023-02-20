package com.dating.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//Thanks to Lombok, we don't need to describe these explicitly anymore!
//Annotations are enough!
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String likes;
	private String dislikes;
	private String hobbies;
	private String profileUrl;
	private String about;
	//this will never be saved to the db
	//we will just use it to find user by id in updateInterest method
	@Transient
	private int userAccountId;
	@OneToOne
	@JoinColumn(name="user_id")
	//to avoid loops
	@JsonIgnore
	private UserAccount userAccount;
}
