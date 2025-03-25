package com.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.DTO.ForgetPassDTO;
import com.DTO.UserLoginDTO;
import com.Entity.LoginDetails;
import com.Repository.LoginDetailsRepository;
import com.Utility.RandomNumberGenerator;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private JavaMailSender mailSender;

	static Map<String,Long> forgetPasswordMap = new HashMap<>();

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public ResponseEntity loginInService(LoginDetails loginDetails) {
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
			UserLoginDTO userLoginDto = new UserLoginDTO();
			if (next.getPassword().equals(loginDetails.getPassword())) {
				modelMapper.map(next.getUser(), userLoginDto);
				return new ResponseEntity<>(userLoginDto, HttpStatus.OK);
			}else
				return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity generateOtpInService(LoginDetails loginDetails) {
		if (loginDetails.getEmail() == null) {
			logger.error("empty login fields error");
			return new ResponseEntity<>("Login fields cant be empty ", HttpStatus.BAD_REQUEST);
		}if(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty()){
			logger.error("User email does not exist");
			return new ResponseEntity<>("User email does not exist ", HttpStatus.BAD_REQUEST);
		}else {
			String otp =RandomNumberGenerator.generateNumber();
			Long a = Long.parseLong(otp);
			sendOtpToViaEmail(otp);
			forgetPasswordMap.put(loginDetails.getEmail(),a);
			return new ResponseEntity<>("Otp send to mail use that at /forgetPassword to reset password", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity forgetPasswordInService(ForgetPassDTO forgetPassDto) {
		if (forgetPassDto == null || forgetPassDto.getEmail() == null) {
			logger.error("empty login fields error");
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
		email.setTo("suniljadhav8306@gmail.com");
		email.setSubject("Otp to Reset Password ");
		email.setText("Your OTP to reset password is: " +otp);
		mailSender.send(email);
	}

}
