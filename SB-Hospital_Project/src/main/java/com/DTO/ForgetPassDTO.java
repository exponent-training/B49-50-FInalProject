package com.DTO;

import lombok.Data;

@Data
public class ForgetPassDTO {

	private String email;
	private long otp;
	private String newPassword;

}
