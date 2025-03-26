package com.example.project1.service;

import com.example.project1.dto.ForgetPassDto;
import com.example.project1.entity.LoginDetails;
import org.springframework.http.ResponseEntity;

public interface  LoginService {
	ResponseEntity<?> loginInService(LoginDetails loginDetails);

	ResponseEntity<?> generateOtpInService(LoginDetails loginDetails);

	ResponseEntity<?> forgetPasswordInService(ForgetPassDto forgetPassDto);
}
