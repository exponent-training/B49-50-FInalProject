package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int uid;
	
	String userNumber;
	
	String firstName;
	
	String lastName;
	
	String address;
	
	int zipCode;
	
	String country;
	
	String gender;
	
	long mobileNumber;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private Roles role;

	
	@Type(type = "yes_no")
	private boolean status;

	
}
