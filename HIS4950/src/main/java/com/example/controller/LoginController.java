package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.LoginDTO;
import com.example.Entity.Login;
import com.example.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService ls;

	@GetMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody Login login)
	{
		LoginDTO loginDetails = ls.loginUserInService(login);
		
		return new ResponseEntity(loginDetails , HttpStatus.OK);
	}
	
	
}
