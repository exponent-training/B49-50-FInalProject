package com.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.LoginDetails;
import com.Entity.User;
import com.Service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService us;
	
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/registerUser")
	public ResponseEntity<?> ResisterUser(@RequestBody LoginDetails loginDetails){
		
	 return  us.RegisterUserInService(loginDetails);
	}	
	
	@PutMapping("/updateUser/{email}")
	public ResponseEntity<?> UpdateUser(@PathVariable int email, @RequestBody User user){
		
		 logger.info("update user" ,email , user);
	    	int result = us.UpdateUserByEmail(user);
	    	
			
			  if(result == 0) { return new ResponseEntity("User Updated Successfully...!",
			  HttpStatus.OK); }else 
			  { 
				 return new ResponseEntity("User is null...!",  HttpStatus.NOT_ACCEPTABLE); }
			 	
	    	 
	}
	
	/*
	 * @DeleteMapping("/deleteUser/{email}") public ResponseEntity<?>
	 * DeleteUserByEmail(@RequestParam ("email") String email){
	 * 
	 * String reult = us.deleteUserByEmail(email); }
	 */
	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<?> deleteUser( @PathVariable String email){
		return us.deleteUserByEmail(email);
	}
}				

