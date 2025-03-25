package com.ServiceIMPL;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Controller.HomeController;
import com.Entity.Roles;
import com.Entity.User;
import com.Entity.UserLogin;
import com.Repository.LoginRepository;
import com.Repository.RoleRepository;
import com.Repository.UserRepository;
import com.Service.UserService;
import com.Util.GenerateUserNumber;

@Service
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private LoginRepository lr;

	@Autowired
	private RoleRepository rr;

	Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

	@Override
	public int RegisterUserInService(UserLogin userlogin) {

		if (userlogin != null && userlogin.getEmail() != null && !userlogin.getEmail().isEmpty()) {

			Optional<UserLogin> existingUser = lr.findByemail(userlogin.getEmail());

			if (existingUser.isPresent()) {
				logger.warn("User already present");
				return 1;
			} else {
				userlogin.getUser().setUserNumber(GenerateUserNumber.getRandomNumber());
				Roles role = rr.findByRoleName("normalUser");

				userlogin.getUser().setRole(role);
				lr.save(userlogin);
				logger.info("user added");
				return 2;

			}

		} else {
			logger.warn("User or Email Sould not be null");
			return 3;

		}

	}

	@Override
	public int updateUserInService(UserLogin user) {

		if (user != null) {
			lr.save(user);
			logger.info("user updated succesfully");
		} else {
			logger.warn("user should not be null");
		}
		return 0;

	}

	@Override
	public Optional<UserLogin> getUserAllData(String email) {
		Optional<UserLogin> findByemail = lr.findByemail(email);
		return findByemail;

	}

	@Override
	public int deleteUserInService(String email) {

		Optional<UserLogin> result = lr.findByemail(email);
		if (result.isPresent()) {
			lr.delete(result.get());
			logger.info("user deleted");
		} else {
			logger.warn("user should not null for performing delete operation");
			return 1;

		}
		return 0;
	}

}
