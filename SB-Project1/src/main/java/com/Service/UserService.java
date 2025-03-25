package com.Service;

import com.Entity.Login;
import com.Entity.User;

public interface UserService {


   public int addUserinService(Login login);
   
   int newPasswordInService(Login login);
   
	public int updatePasswordInService(String otp,String password) ;

	int updateUserInSerive(Login login);

	int deleteUserInSerivce(String email);
}
