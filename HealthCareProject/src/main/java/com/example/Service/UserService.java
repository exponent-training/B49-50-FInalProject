package com.example.Service;

import com.Entity.Login;
import com.Entity.User;
import com.example.DTO.ResponseDTO;

public interface UserService {

	//public ResponseDTO registerUserinService(User user);

	public ResponseDTO registerUserinService(Login login);

	
}
