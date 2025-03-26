package com.example.project1.controller;

import com.example.project1.entity.LoginDetails;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@Autowired
	private UserService uService;

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody LoginDetails loginDetails) {
		return uService.registerInService(loginDetails);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody LoginDetails loginDetails) {
		return uService.updateUserInService(loginDetails);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestBody LoginDetails loginDetails) {
		return uService.deleteUserInService(loginDetails);
	}
}
