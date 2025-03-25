package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Login;
import com.Entity.PasswordResetToken;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Repository.PasswordResetTokenRepository;
import com.Repository.UserRepository;
import com.Service.EmailServiceImpl;
import com.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordResetTokenRepository prtr;

	@Autowired
	private UserRepository ur;

	@Autowired
	private LoginRepository lr;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestParam String email) {

		if (userService.isEmailRegistered(email)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not registered");
		}

		// Generate the password reset token
		String token = userService.createPasswordResetToken(email);

		// Send the reset link to the user's email
		String resetLink = "http://localhost:5200/api/auth/reset-password?token=" + token;

		emailServiceImpl.sendPasswordResetEmail(email, resetLink);

		return ResponseEntity.ok("Password reset link has been sent to your email");

	}

	// Reset password endpoint

	@PostMapping("/resetPassword/{token}/{newPassword}")
	public ResponseEntity<String> resetPassword(@PathVariable String token, @PathVariable String newPassword) {

		// Validate token
		PasswordResetToken resetToken = prtr.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Invalid or expired token"));

		// Debugging: Log token details
		System.out.println("Reset token found for email: " + resetToken.getEmail());

		// Find the user associated with the token
		Login logEmail = lr.findByEmail(resetToken.getEmail());
		System.out.println("User not found for email: " + resetToken.getEmail());

		// Debugging: Log user details
		System.out.println("User found: " + logEmail.getEmail());

		/*
		 * // Hash the new password before saving String hashedPassword =
		 * passwordEncoder.encode(newPassword);
		 */
		logEmail.setPassword(newPassword);

		// Save updated user
		lr.save(logEmail);

		// Invalidate the token after use
		prtr.delete(resetToken);

		return ResponseEntity.ok("Password reset successfully");
	}

}
