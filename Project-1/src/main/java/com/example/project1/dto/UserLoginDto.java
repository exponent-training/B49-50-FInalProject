package com.example.project1.dto;

import lombok.Data;

@Data
public class UserLoginDto {
	private String userNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String mobileNumber;
}
