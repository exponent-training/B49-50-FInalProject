package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ResponseDTO;
import com.example.Entity.Login;
import com.example.Entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/exponent/api/user")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody Login login) {

		System.out.println(login);

		ResponseDTO response = us.registerUserinService(login);

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}



}
