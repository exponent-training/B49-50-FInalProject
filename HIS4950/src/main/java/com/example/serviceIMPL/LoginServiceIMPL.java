package com.example.serviceIMPL;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.LoginDTO;
import com.example.Entity.Login;
import com.example.Repo.LoginRepo;
import com.example.service.LoginService;

@Service
public class LoginServiceIMPL implements LoginService {

	@Autowired
	private LoginRepo lr;

	@Override
	public LoginDTO loginUserInService(Login login) {

		LoginDTO ldt = new LoginDTO();

		if (login != null && login.getEmail() != null) {
			Login details = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());

			if (details != null) {
				
//				ModelMapper mm = new ModelMapper();
//				mm.map(details.getUser(), ldt);
				
				// task :- ( 1. Login basis of status || normalUser role)
				
				System.out.println(ldt);
				
				ldt.setFirstName(details.getUser().getFirstName());
				ldt.setMobNumber(details.getUser().getMobNumber());
				ldt.setAddress(details.getUser().getAddress());

				return ldt;

			} else {
				ldt.setMsg("Credential invalid");
				return ldt;

			}

		} else {

			ldt.setMsg("Contact your admin email is empty");
			return ldt;
		}

	}

}
