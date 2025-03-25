package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

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
	private String zipcode;
	private String country;
	private String gender;
	private String mobileNo;

	@OneToOne(cascade = CascadeType.DETACH)
	//@JoinColumn(name = "role_rid")
	private Roles role;
		
	@Type(type = "yes_no")
	private boolean status;

}
