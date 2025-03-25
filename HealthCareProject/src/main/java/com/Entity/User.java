package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(name = "User")
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

	private String cnt;

	private String gender;

	private String mobnumber;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private Roles role; 

	// @NotBlank(message = "This Field should not be blank")
	private String email;

	private String password;

	@Type(type = "yes_no")
	 private boolean status;	
}
