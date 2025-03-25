package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.LoginDTO;
import com.Entity.Login;
import com.Service.ILoginService;

@RestController
@RequestMapping("/HealthCare")
public class LoginController {

	@Autowired
	private ILoginService loginservice;

	@GetMapping("/loginUser")
	public ResponseEntity<?> LoginUser(@RequestBody Login login) {
		LoginDTO loginDetails = loginservice.loginUserInService(login);

		return new ResponseEntity(loginDetails, HttpStatus.OK);
	}

}
