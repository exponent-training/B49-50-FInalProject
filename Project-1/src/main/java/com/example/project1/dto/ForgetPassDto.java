package com.example.project1.dto;

import lombok.Data;

@Data
public class ForgetPassDto {
	private String email;
	private long otp;
	private String newPassword;
}
