package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTOLayer.LoginDTO;
import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.Service.LoginService;

@RestController
public class LoginController {
	@Autowired
	public LoginService ls;
	

	@PostMapping("/Login")
	public ResponseEntity<ResponseDTO> Registration(@RequestBody Login  login) {

		ResponseDTO RDTO=ls.LoginPatientOrDoctor(login);

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

	
	@GetMapping("/getlogindetail")
	public ResponseEntity<?> Getlogindata(@RequestBody Login login){
		
//		LoginDTO LoginDetails=ls.getLogindetails(login);
		
	    LoginDTO LoginDetails=ls.getLogindetails(login);
		
		if("Credentials invalid".equals(LoginDetails.getMsg())||"Contact your admin email is empty".equals(LoginDetails.getMsg())) {
			
			return new ResponseEntity("Enter valid data",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(LoginDetails,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/update")
   public ResponseEntity<?> UpdateUser(@RequestParam String email,@RequestBody User user){
	   
		 try {
	            User newUser = ls.updateUserthroughLogin(email, user);

	            if (newUser == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("{\"error\": \"User not found\"}");
	            }

	            return ResponseEntity.ok(newUser);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("{\"error\": \"" + e.getMessage() + "\"}");
	        }
	   
   }
}
