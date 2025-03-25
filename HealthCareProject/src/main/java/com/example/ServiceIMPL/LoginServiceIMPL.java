package com.example.ServiceIMPL;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Login;
import com.example.DTO.LoginDTO;
import com.example.Repository.LoginRepostiory;
import com.example.Service.LoginService;

@Service
public class LoginServiceIMPL implements LoginService 
{
	@Autowired
	private LoginRepostiory lr;

	@Override
	public LoginDTO loginUserInService(Login login)
	{
		LoginDTO ldt = new LoginDTO();
		
		if(login != null && login.getEmail() != null)
		{
			Login details = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());
			
			if(details == null)
			{
				//ModelMapper mm = new ModelMapper();
				//mm.map(details.getUser(), ldt);
				
				System.out.println(ldt);
				
				ldt.setUsername(details.getUser().getFirstName());
				ldt.setMobNumber(details.getUser().getMobnumber());
				ldt.setAddress(details.getUser().getAddress());
				
				return ldt;
			}
			else
			{
				ldt.setMsg("Credtial Invalid");
				return ldt;
			
			}
		}
		else
		{
			ldt.setMsg("Contact Your Admin email is empty");
			return ldt;
		}
		
	}

}
