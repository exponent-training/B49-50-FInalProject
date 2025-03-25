package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;

	// Method to send password reset email
	public void sendPasswordResetEmail(String to, String resetLink) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject("Password reset request");
		message.setText("Click the link to reset your password" + resetLink);

		mailSender.send(message);
	}
}
