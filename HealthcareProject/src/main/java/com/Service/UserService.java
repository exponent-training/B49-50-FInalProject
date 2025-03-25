package com.Service;

import java.util.List;

import com.DTO.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;

import net.sf.jasperreports.engine.JRException;

public interface UserService {

//	public ResponseDTO userRegisterInService(User user);

	public ResponseDTO userRegisterInService(Login login);

	public boolean isEmailRegistered(String email);

	public String createPasswordResetToken(String email);

	public User updateUserByEmail(String email, User user);

	public void deleteUserByEmail(String email);

}
