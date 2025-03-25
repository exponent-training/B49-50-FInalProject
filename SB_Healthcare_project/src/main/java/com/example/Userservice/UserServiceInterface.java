package com.example.Userservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Entity.Login;
import com.example.Entity.User;

public interface UserServiceInterface {

	



	public Login findByEmail(String email);

	public void RegisterUser(Login login);

	public Login loginUser(String email, String password);

	public Login forgotpassword(Login login1);

	public Login updateuser(Login login);

	public void DeleteUser(Login login);

	public User updateRole(User u);

	

	

}
