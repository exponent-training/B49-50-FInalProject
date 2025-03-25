package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Login;
import com.Entity.User;
import com.Repository.RolesRepository;
import com.Service.UserService;

@RestController
@RequestMapping("/exponent")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/register")
	public ResponseEntity<?> postUser(@RequestBody Login login) {

		int result = us.addUserinService(login);

		if (result == 2) {
			return new ResponseEntity("User added successfully!!", HttpStatus.OK);
		}
		if (result == 1) {
			return new ResponseEntity("User already Exist!!", HttpStatus.BAD_REQUEST);

		}
		if (result == 3) {
			return new ResponseEntity("please fill all the fields!!", HttpStatus.BAD_REQUEST);
		}
		return null;

	}
	
	@GetMapping("/forgetPassWord")
	public ResponseEntity<?> forgetpassword(@RequestBody Login login) {

		int n = us.newPasswordInService(login);

		if (n == 1) {
			return new ResponseEntity("Enter your OTP!!", HttpStatus.OK);
		} else if (n == 2) {
			return new ResponseEntity("InCurrect EmailId!!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("EmailId should not be empty!!", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestParam("otp")String otp,@RequestParam("password")String password) {

		int n = us.updatePasswordInService(otp,password);

		if (n == 1) {
			return new ResponseEntity("PassWord Updated!!", HttpStatus.OK);
		}else {
			return new ResponseEntity("Incorrect OTP!!", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody Login login) {

		int n = us.updateUserInSerive(login);

		if (n == 1) {
			return new ResponseEntity<>("User updated!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Wrrong EmailId!", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestParam("email") String email) {

		int n = us.deleteUserInSerivce(email);

		if (n == 1) {
			return new ResponseEntity("User deleted successfuly!!", HttpStatus.OK);
		} else {
			return new ResponseEntity("Incorrect Email Id!!", HttpStatus.BAD_REQUEST);
		}

	}

}
