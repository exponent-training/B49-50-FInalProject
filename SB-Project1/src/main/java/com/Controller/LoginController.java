package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.DTOs.DTO;
import com.Entity.Login;
import com.Service.LoginService;

@RequestMapping("/exponent")
@RestController
public class LoginController {

	@Autowired
	private LoginService ls;

	@GetMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody Login login) {

		DTO user = ls.loginUserService(login);

		if (user != null) {
			return new ResponseEntity(user, HttpStatus.OK);
		} else {
			return new ResponseEntity("User not found!! ", HttpStatus.BAD_REQUEST);
		}

	}


}
