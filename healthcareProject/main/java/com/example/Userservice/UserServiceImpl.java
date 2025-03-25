package com.example.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Login;
import com.example.Entity.User;
import com.example.repository.LoginRepository;
import com.example.repository.userRepository;

@Service
public class UserServiceImpl  implements UserServiceInterface{
 
	@Autowired
	private userRepository ur;
	@Autowired
	private LoginRepository lr;
	
	
	@Override
	public void RegisterUser(Login login) {
		lr.save(login);
		
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

	@Override
	public Login updateuser(Login login) {
		Login l=lr.save(login);
		return l;
	}

	@Override
	public void DeleteUser(Login login) {
		lr.delete(login);
		
	}

	@Override
	public Login loginUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateRole(User u) {
		User user=ur.save(u);
		return user;
	}

	
	
	
	
	
	
	
}
