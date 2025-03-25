package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void SendEmail(String to,String message) {
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setTo(to);
		smm.setText(message);
		mailSender.send(smm);
	}

}
