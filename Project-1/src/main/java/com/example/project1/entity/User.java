package com.example.project1.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String userNumber;
	private String firstName;
	private String lastName;
	private String address;
	private long zipCode;
	private String country;
	private String gender;
	private String mobileNumber;
	@OneToOne(cascade = CascadeType.DETACH)
	private  Roles role;
	@Type(type = "yes_no")
	private boolean status;
}
