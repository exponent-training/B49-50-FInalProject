package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.LoginDTO;
import com.Entity.Login;
import com.Repository.ILoginRepository;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private ILoginRepository loginrepository;

	@Override
	public LoginDTO loginUserInService(Login login) {

		LoginDTO ldt = new LoginDTO();

		if (login != null && login.getEmail() != null) {
			Login details = loginrepository.findByEmailAndPassword(login.getEmail(), login.getPassword());

			if (details != null) {
				ldt.setUserName(details.getUser().getFirstName());
				ldt.setAddress(details.getUser().getAddress());
				ldt.setMobNumber(details.getUser().getMobNumber());
				
				//ldt.setMobNumber(details.getUser().getMobNumber());

				return ldt;
			} else {
				ldt.setMsg("Credentials invalid");
				return ldt;
			}

		} else {
			ldt.setMsg("contact your admin , Email is Empty");
			return ldt;
		}

	}

}
