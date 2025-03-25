package com.ServiceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTOS.Dtos;
import com.Entity.UserLogin;
import com.Repository.LoginRepository;
import com.Service.UserLoginService;

@Service
public class UserLoginServiceIMPL implements UserLoginService {

	@Autowired
	private LoginRepository lr;

	Dtos dto = new Dtos();

	@Override
	public Dtos getUserLoginInService(UserLogin userlogin) {
		Dtos dto = new Dtos(); // Initialize Dtos instance

		if (userlogin == null || userlogin.getEmail() == null || userlogin.getEmail().isEmpty()
				|| userlogin.getPassword() == null || userlogin.getPassword().isEmpty()) {
			dto.setMsg("Contact your admin, email or password is empty");
			return dto;
		}

		UserLogin user = lr.findByEmailAndPassword(userlogin.getEmail(), userlogin.getPassword());

		if (user != null && user.getUser() != null) {
			dto.setUsernumber(user.getUser().getUserNumber());
			dto.setMobileno(user.getUser().getMobileNo());
			dto.setAddress(user.getUser().getAddress());
		} else {
			dto.setMsg("Invalid credentials");
		}

		return dto;
	}

}
