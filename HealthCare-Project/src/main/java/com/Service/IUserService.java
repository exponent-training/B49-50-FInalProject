package com.Service;

import com.DTO.ResponseDTO;
import com.Entity.Login;

public interface IUserService {

	public ResponseDTO registerUserInService(Login login);
}
