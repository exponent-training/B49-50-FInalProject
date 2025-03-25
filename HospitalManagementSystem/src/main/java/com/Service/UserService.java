package com.Service;

import java.util.Optional;

import com.Entity.User;
import com.Entity.UserLogin;

public interface UserService {

	int RegisterUserInService(UserLogin userlogin);

	int updateUserInService(UserLogin user);

	Optional<UserLogin> getUserAllData(String email);

	int deleteUserInService(String email);

}
