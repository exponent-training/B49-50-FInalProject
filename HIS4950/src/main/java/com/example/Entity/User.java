package com.example.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;

	private String userNumber;

	private String firstName;

	private String lastName;

	private String address;

	private int zipcode;

	private String cnt;

	private String gender;

	private String mobNumber;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private Roles role;

	@Type(type = "yes_no")
	private boolean status;

}
