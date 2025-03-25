package com.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.User;
import com.Entity.UserLogin;
import com.Service.UserService;

@RestController
@RequestMapping("/Exponent")
public class HomeController {

	@Autowired
	private UserService us;

	@PostMapping("/register")
	public ResponseEntity<?> RegisrationUser(@RequestBody UserLogin userlogin) {
		int addedUser = us.RegisterUserInService(userlogin);
		if (addedUser == 1) {
			return new ResponseEntity("user email alredy exist", HttpStatus.CONFLICT);
		}
		if (addedUser == 2) {
			return new ResponseEntity("user added", HttpStatus.CREATED);
		}
		if (addedUser == 3) {
			return new ResponseEntity("user or email should not be null", HttpStatus.BAD_REQUEST);

		}
		return null;

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserLogin user) {

		int updatedUser = us.updateUserInService(user);
		if (updatedUser == 0) {
			return new ResponseEntity("user updated succesfully by email", HttpStatus.OK);
		} else {

			return new ResponseEntity("user  is null", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getUserAllData")
	public ResponseEntity<?> updateUser(@RequestParam("email") String email) {

		Optional<UserLogin> userAllData = us.getUserAllData(email);
		return new ResponseEntity(userAllData, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam("email") String email) {

		int deletedUser = us.deleteUserInService(email);
		if (deletedUser == 0) {
			return new ResponseEntity("user Deleted Using Email ID", HttpStatus.OK);

		}

		else {
			return new ResponseEntity("inavalid mailId please entered valid mail ID", HttpStatus.BAD_REQUEST);
		}

	}
}
