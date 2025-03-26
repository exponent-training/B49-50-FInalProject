package com.example.project1.serviceImpl;

import com.example.project1.dto.ForgetPassDto;
import com.example.project1.dto.UserLoginDto;
import com.example.project1.entity.LoginDetails;
import com.example.project1.repository.LoginDetailsRepository;
import com.example.project1.service.LoginService;
import com.example.project1.utility.RandomNumberGenerator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private JavaMailSender mailSender;

	static final Map<String,Long> forgetPasswordMap = new HashMap<>();

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public ResponseEntity<?> loginInService(LoginDetails loginDetails) {
		if (loginDetails == null || loginDetails.getEmail() == null || loginDetails.getPassword() == null) {
			logger.error("empty login fields error");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		}
		else if(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty()){
			logger.error("User email does not exist");
			return new ResponseEntity<>("User email does not exist ", HttpStatus.BAD_REQUEST);
		}
		else {
			Iterator<LoginDetails> iterator = loginDetailsRepository.findByEmail(loginDetails.getEmail()).iterator();
			LoginDetails next = iterator.next();
			UserLoginDto userLoginDto = new UserLoginDto();
			if (!(next.getUser().isStatus()) || next.getUser().getRole().getRoleName().equals("default")) {
				return new ResponseEntity<>("User has no role contact admin", HttpStatus.BAD_REQUEST);
			}
			else if (next.getPassword().equals(loginDetails.getPassword())) {
				modelMapper.map(next.getUser(), userLoginDto);
				return new ResponseEntity<>(userLoginDto, HttpStatus.OK);
			}else
				return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> generateOtpInService(LoginDetails loginDetails) {
		if (loginDetails.getEmail() == null) {
			logger.error("empty login fields ");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		}if(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty()){
			logger.error("User mail does not exist");
			return new ResponseEntity<>("User email does not exist ", HttpStatus.BAD_REQUEST);
		}else {
			String otp = RandomNumberGenerator.generateNumber();
			Long a = Long.parseLong(otp);
			sendOtpToViaEmail(otp);
			forgetPasswordMap.put(loginDetails.getEmail(),a);
			return new ResponseEntity<>("Otp send to mail use that at /forgetPassword to reset password", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> forgetPasswordInService(ForgetPassDto forgetPassDto) {
		if (forgetPassDto == null || forgetPassDto.getEmail() == null) {
			logger.error("empty  forget Password fields error");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		}
		if (forgetPasswordMap.containsKey(forgetPassDto.getEmail())) {
			long remove = forgetPasswordMap.remove(forgetPassDto.getEmail());
			if (remove==forgetPassDto.getOtp()) {
				LoginDetails login = loginDetailsRepository.getLoginDetailsByEmail(forgetPassDto.getEmail());
				login.setPassword(forgetPassDto.getNewPassword());
				loginDetailsRepository.save(login);
				String msg = forgetPassDto.getEmail()+" has changed Password to "+forgetPassDto.getNewPassword();
				logger.info(msg);
				return new ResponseEntity<>("password changed", HttpStatus.OK);
			}else
				return new ResponseEntity<>("Wrong OTP", HttpStatus.BAD_REQUEST);
		}else
			return new ResponseEntity<>("Wrong email", HttpStatus.BAD_REQUEST);
	}

	public void sendOtpToViaEmail(String otp) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("pranaybhalekar21@gmail.com");
		email.setSubject("Otp to Reset Password ");
		email.setText("Your OTP to reset password is: " +otp);
		mailSender.send(email);
	}
}
