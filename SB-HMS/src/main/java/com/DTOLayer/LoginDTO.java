package com.DTOLayer;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//@Entity
@Data
@JsonInclude(value=Include.NON_NULL)
public class LoginDTO {
	
	@JsonProperty(value="Fname")
	private String firstName;
	@JsonProperty(value="LName")
	private String lastName;
	@JsonProperty(value="Add")
	private String Address;
	@JsonProperty(value="mobNo.")
	private long mobNumber;
	@JsonProperty(value="msg")
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		 this.msg = msg;
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
	public long getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(long mobNumber) {
		this.mobNumber = mobNumber;
	}

}
