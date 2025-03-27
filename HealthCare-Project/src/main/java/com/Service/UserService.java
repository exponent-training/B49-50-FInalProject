package com.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.Repository.ILoginRepository;
import com.Repository.IUserRepository;
import com.util.GenerateUserNumber;

@Service
public class UserService implements IUserService {

	/*
	 * @Autowired private IUserRepository userRepository;
	 */
	@Autowired
	private ILoginRepository loginRepository;

	public static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public ResponseDTO registerUserInService(Login login) {
		ResponseDTO response = new ResponseDTO();

		Login ufromDB = loginRepository.findByEmail(login.getEmail());

		if (login.getUser() != null && login.getEmail() != null) {
			if (ufromDB == null) {

				login.getUser().setUserNumber(GenerateUserNumber.getRandomNumber());

				loginRepository.save(login);

				response.setMsg("User Added Successfully");

				return response;

			} else {
				response.setMsg("User already exist");
				return response;
			}
		} else {
			response.setMsg("User is not registered beacause mail is empty");
			return response;
		}
	}

}
