package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value = "lid")
	private int lid;
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "password")
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid", referencedColumnName = "uid")  // Use correct PK column name
	@JsonProperty(value="user")
	private User user;

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
