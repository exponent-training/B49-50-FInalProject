package com.Entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;
@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uid")  // Change the column name to match the referenced column in Login
	@JsonProperty(value="user_id")
	private int user_id;
	@JsonProperty(value="Uno.")
	private String userNumber;
	@JsonProperty(value="Fname")
	private String firstName;
	@JsonProperty(value="LName")
	private String lastName;
	@JsonProperty(value="Add")
	private String Address;
	@JsonProperty(value="Zcode")
	private String Zipcode;
	@JsonProperty(value="Country")
	private String country;
	@JsonProperty(value="Sex")
	private String Gender;
	@JsonProperty(value="mobNo.")
	private long mobNumber;
//	@Column(unique = true,nullable=false)
//	@JsonProperty(value="email")
//	private String email;
//	@JsonProperty(value="password")
//	private String password;
//	
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(nullable = false)
	@JsonProperty(value="status")
	private boolean status;
	

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public long getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(long mobNumber) {
		this.mobNumber = mobNumber;
	}
	
	
	

}
