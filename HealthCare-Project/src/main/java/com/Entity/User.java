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
	private int uid;

	private String userNumber;

	private String firstName;

	private String lastName;

	private String address;

	private int zipcode;

	private String country;

	private String gender;

	private int mobNumber;

	@OneToOne(cascade = CascadeType.DETACH)
	private Roles role;
	
	@Type(type = "yes_no")
	private boolean status;
}
