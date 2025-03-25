package com.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.ResponseDTO;
import com.Entity.Login;
import com.Service.IUserService;


@RestController
@RequestMapping("/HealthCare")

public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private IUserService userService;

	@PostMapping("/RegisterUser")
	public ResponseEntity<ResponseDTO> RegisterUser(@RequestBody Login login) {
		System.out.println(login);
		
		ResponseDTO response = userService.registerUserInService(login);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.OK);
	}
}
