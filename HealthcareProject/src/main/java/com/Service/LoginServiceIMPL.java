package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.LoginDTO;
import com.Entity.Login;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Repository.UserRepository;

@Service
public class LoginServiceIMPL implements LoginService {

	@Autowired
	private LoginRepository lr;

	@Autowired
	private UserRepository ur;

	@Override
	public LoginDTO loginuserInService(Login login) {

		LoginDTO ldt = new LoginDTO();

		if (login != null && login.getEmail() != null) {
			Login details = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());

			if (details != null) {

				System.out.println(ldt);

				ldt.setFirstName(details.getUser().getFirstName());
				ldt.setMobileno(details.getUser().getMobileno());
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
