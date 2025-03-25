package com.Service;

import org.springframework.http.ResponseEntity;

import com.DTOLayer.LoginDTO;
import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;

public interface LoginService {

	ResponseDTO LoginPatientOrDoctor(Login login);
	
	LoginDTO getLogindetails(Login login);

//	User updateUserthroughLogin(String email, Login login);

	User updateUserthroughLogin(String email, User user);

	

}
