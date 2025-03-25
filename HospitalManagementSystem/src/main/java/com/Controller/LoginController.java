package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.DTOS.Dtos;
import com.Entity.UserLogin;
import com.Service.UserLoginService;

import lombok.Getter;

@RestController
@RequestMapping("/Exponent")
public class LoginController {

	@Autowired
	private UserLoginService us;

	@GetMapping("/login")
	public ResponseEntity<?> getLogin(@RequestBody UserLogin userlogin) {
		Dtos result = us.getUserLoginInService(userlogin);

		if (result != null) {
			return new ResponseEntity(result, HttpStatus.OK);
		} else {
			return new ResponseEntity("user not found", HttpStatus.BAD_REQUEST);
		}

	}

}
