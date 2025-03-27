package com.example.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int did;
	private String dname;
	private int age;
	private String gender;
	private String speciliazation;
	private String experience;
	private String language;
	private int mobileNo;
	private String email;
	private String schedule;
	@OneToOne(cascade = CascadeType.ALL)
	private Roles role;
	
	
	
}
