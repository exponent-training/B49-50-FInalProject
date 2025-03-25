package com.Service;

import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;

public interface UserService {

	ResponseDTO LoginPatientOrDoctor(User user);
	
//	public boolean isUserExist(String email);

}
