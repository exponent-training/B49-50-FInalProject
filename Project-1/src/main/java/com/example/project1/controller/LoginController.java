package com.example.project1.controller;

import com.example.project1.dto.ForgetPassDto;
import com.example.project1.entity.LoginDetails;
import com.example.project1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
	@Autowired
	private LoginService lService;

	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) {
		return lService.loginInService(loginDetails);
	}

	@PostMapping("/generateOtp")
	public ResponseEntity<?> generateOtp(@RequestBody LoginDetails loginDetails) {
		return lService.generateOtpInService(loginDetails);
	}

	@PutMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestBody ForgetPassDto forgetPassDto) {
		return lService.forgetPasswordInService(forgetPassDto);
	}
}
