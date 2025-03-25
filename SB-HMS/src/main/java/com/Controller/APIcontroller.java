package com.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.Service.UserService;

@RestController
public class APIcontroller {
	@Autowired
	private UserService Us;
	
	

	Logger logger = LoggerFactory.getLogger(APIcontroller.class);

	@PostMapping("/Register")
	public ResponseEntity<ResponseDTO> Registration(@RequestBody User  user) {

		ResponseDTO RDTO=Us.LoginPatientOrDoctor(user);

//		if(result==0) {
//			
//			return new ResponseEntity("User Register successfully!!!",HttpStatus.OK);
//		}
//		else
//		{
//			return new ResponseEntity("User or email is empty",HttpStatus.NO_CONTENT);
//		}
//		
		return new ResponseEntity<ResponseDTO>(RDTO, HttpStatus.OK);
//		
	}

}
