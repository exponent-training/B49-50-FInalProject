package com.example.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Login;
import com.example.Entity.Roles;
import com.example.Entity.User;
import com.example.repository.LoginRepository;

@Service
public class LoginserviceImpl implements LoginserviceInterface{
     @Autowired
     private LoginRepository lr;
	@Override
	public Login loginUser(String email, String password) {
		Login l=lr.findByEmailAndPassword(email, password);
		return l;
	}
	@Override
	public Login findByEmail(String email) {
		Login l =lr.findByEmail(email);
		return l;
	}
	@Override
	public Login forgotpassword(Login login1) {
	         
		Login login=lr.save(login1);
		return login;
	}
	

	
	
	
	
	
	
	
}
