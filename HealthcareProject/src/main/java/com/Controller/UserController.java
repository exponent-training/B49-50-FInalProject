package com.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Service.LoginService;
import com.Service.UserService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/exponent/api/user")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody Login login) {

		System.out.println(login);

		ResponseDTO response = us.userRegisterInService(login);

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_ACCEPTABLE);

	}

	@PutMapping("/update/{email}")
	public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
		try {
			User updatedUser = us.updateUserByEmail(email, user);
			return ResponseEntity.ok(updatedUser);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		try {
			us.deleteUserByEmail(email);
			return ResponseEntity.ok("User deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	

	@GetMapping("/generatePatientReport/{format}")
	public ResponseEntity<?> generatePatientReport(@PathVariable String format) throws JRException {

		us.generatePatientReportInservice(format);
		return new ResponseEntity("Report created for Patient", HttpStatus.CREATED);

	}



	@GetMapping("/generateUserReport/{format}")
	public ResponseEntity<?> generateUserReport(@PathVariable String format) throws JRException {

		us.generateUserReportInService(format);

		return new ResponseEntity("Report created for user", HttpStatus.CREATED);

	}
}
