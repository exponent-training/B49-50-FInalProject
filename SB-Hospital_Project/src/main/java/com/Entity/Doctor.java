package com.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int did;
	
	private String dName;
	
	@JsonProperty(value = "dg")
	private String dGender;
	
	private String specializist;
	
	private String experince;
	
	private String languages;
	
	private long mobileNumber;
	
	@JsonProperty(value = "de")
	private String dEmail;
	
	
	private String schedule;
	
	
}
