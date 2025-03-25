package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;
	
	private String email;
	
	private String password;
	
	private String otp;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	
}
