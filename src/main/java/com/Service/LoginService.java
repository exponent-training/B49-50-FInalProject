package com.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.DTO.ForgetPassDTO;
import com.Entity.LoginDetails;

@Service
public interface LoginService {

	ResponseEntity loginInService(LoginDetails loginDetails);

	ResponseEntity generateOtpInService(LoginDetails loginDetails);

	ResponseEntity forgetPasswordInService(ForgetPassDTO forgetPassDto);

	

}
