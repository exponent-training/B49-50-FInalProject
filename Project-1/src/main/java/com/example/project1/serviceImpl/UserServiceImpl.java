package com.example.project1.serviceImpl;

import com.example.project1.entity.LoginDetails;
import com.example.project1.repository.LoginDetailsRepository;
import com.example.project1.service.UserService;
import com.example.project1.utility.RandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	@Override
	public ResponseEntity<?> registerInService(LoginDetails loginDetails) {
		if (loginDetails == null || loginDetails.getUser() == null) {
			logger.error("object of Login or user is null");
			return new ResponseEntity<>("User cant be null", HttpStatus.BAD_REQUEST);
		}
		 if (loginDetails.getEmail() == null) {
			 logger.error("User email is null");
			return new ResponseEntity<>("Email cant be null", HttpStatus.BAD_REQUEST);
		}
		 if (!(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty())) {
			 logger.error("User email already exists");
			return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
		}
		 loginDetails.getUser().setUserNumber(RandomNumberGenerator.generateNumber());
		 loginDetails.getUser().getRole().setId(1);
		 loginDetailsRepository.save(loginDetails);
		 logger.info("User saved");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("pranaybhalekar21@gmail.com");
		message.setSubject("User registered");
		message.setText("Your account has been created"+loginDetails.getUser().getUserNumber()+" "+loginDetails.getUser().getFirstName()+" "+loginDetails.getUser().getLastName());
		mailSender.send(message);
		return new ResponseEntity<>("User successfully added",HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> updateUserInService(LoginDetails loginDetails) {
		if (loginDetails == null || loginDetails.getEmail() == null || loginDetails.getPassword() == null) {
			logger.error("empty login fields error");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		}
		else if(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty()){
			logger.error("User email does not exist");
			return new ResponseEntity<>("User email does not exist ", HttpStatus.BAD_REQUEST);
		}
		else {
			LoginDetails dbLD = loginDetailsRepository.getLoginDetailsByEmail(loginDetails.getEmail());
			if(loginDetails.getPassword().equals(dbLD.getPassword())){
				dbLD.getUser().setAddress(loginDetails.getUser().getAddress());
				dbLD.getUser().setCountry(loginDetails.getUser().getCountry());
				dbLD.getUser().setGender(loginDetails.getUser().getGender());
				dbLD.getUser().setFirstName(loginDetails.getUser().getFirstName());
				dbLD.getUser().setLastName(loginDetails.getUser().getLastName());
				dbLD.getUser().setUserNumber(loginDetails.getUser().getUserNumber());
				loginDetailsRepository.save(dbLD);
				logger.info("User updated");
				return new ResponseEntity<>("User updated",HttpStatus.OK);
			}else
				return new ResponseEntity<>("Wrong Password",HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteUserInService(LoginDetails loginDetails) {
		if (loginDetails == null || loginDetails.getEmail() == null || loginDetails.getPassword() == null) {
			logger.error("Email or password fields cant be empty");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		} else if (loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty()) {
			logger.error("User does not exist");
			return new ResponseEntity<>("User email does not exist ", HttpStatus.BAD_REQUEST);
		} else {
			LoginDetails dbLD = loginDetailsRepository.getLoginDetailsByEmail(loginDetails.getEmail());
			if (loginDetails.getPassword().equals(dbLD.getPassword())) {
			loginDetailsRepository.delete(dbLD);
			logger.info("User deleted");
			return new ResponseEntity<>("User deleted",HttpStatus.OK);
			} else
				return new ResponseEntity<>("Wrong Password", HttpStatus.BAD_REQUEST);
		}
	}
}
