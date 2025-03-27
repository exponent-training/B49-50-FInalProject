package com.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int did;

	public int age;

	public String gender;

	public String specialization;

	public String experience;

	public String language;

	public String mobileNo;

	public String email;

	public String schedule;

}
