package com.example.serviceIMPL;

import javax.persistence.GeneratedValue;

import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.ResponseDTO;
import com.example.Entity.Login;
import com.example.Entity.Roles;
import com.example.Entity.User;
import com.example.Repo.LoginRepo;
import com.example.Repo.RoleRepository;
import com.example.Repo.UserRepo;
import com.example.Util.GenerateUserNumber;
import com.example.service.UserService;

@Service
public class UserServiceIMPL implements UserService {

	/*
	 * @Autowired private UserRepo ur;
	 */

	@Autowired
	private LoginRepo lr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private UserRepo ur;

	@Override
	public ResponseDTO registerUserinService(Login login) {
		ResponseDTO response = new ResponseDTO();

		Login ufromDB = lr.findByEmail(login.getEmail());

		if (login.getUser() != null && login.getEmail() != null) {
			if (ufromDB == null) {

				login.getUser().setUserNumber(GenerateUserNumber.getrandomNumber());

				Roles roles = rr.findByRoleName("normalUser");

				User user = login.getUser();
				user.setRole(roles);

//				ur.save(user);

				lr.save(login);

				response.setMsg("User Added successfully");

				return response;

			} else {
				response.setMsg("user already existed");
				return response;
			}

		} else {
			response.setMsg("User not Registered because Email is empty");

			return response;
		}

	}

}
