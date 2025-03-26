package com.example.project1.service;

import com.example.project1.entity.LoginDetails;
import org.springframework.http.ResponseEntity;

public interface UserService {
	ResponseEntity<?> registerInService(LoginDetails loginDetails);

	ResponseEntity<?> updateUserInService(LoginDetails loginDetails);

	ResponseEntity<?> deleteUserInService(LoginDetails loginDetails);
}
