package com.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Entity.LoginDetails;
import com.Entity.User;

@Service
public interface UserService {

	

	ResponseEntity RegisterUserInService(LoginDetails loginDetails);

	int UpdateUserByEmail(User user );

	ResponseEntity<?> deleteUserByEmail(String email);


	

}
