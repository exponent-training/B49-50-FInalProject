package com.Controller;

import com.DTO.ForgetPassDTO;
import com.Entity.LoginDetails;
import com.Service.LoginService;
import com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

	@RestController
	public class LoginController {
		
		@Autowired
		private LoginService lService;

		@GetMapping("/login")
		public ResponseEntity login(@RequestBody LoginDetails loginDetails) {
			return lService.loginInService(loginDetails);
		}

		@PostMapping("/generateOtp")
		public ResponseEntity generateOtp(@RequestBody LoginDetails loginDetails) {
			return lService.generateOtpInService(loginDetails);
		}

		@PutMapping("/forgetPassword")
		public ResponseEntity forgetPassword(@RequestBody ForgetPassDTO forgetPassDto) {
			return lService.forgetPasswordInService(forgetPassDto);
		}
	

}
