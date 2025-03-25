package com.example.ServiceIMPL;

import org.apache.catalina.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Roles;
import com.example.Repository.RoleRepository;
import com.example.Service.RoleService;

@Service
public class RoleServiceIMPL implements RoleService
{
	@Autowired
	private RoleRepository rr;
	
	Logger logger = LoggerFactory.getLogger(RoleServiceIMPL.class);

	@Override
	public void addRolesInService(Roles roles) 
	{
		String roleName = roles.getRoleName();
		
		//Role dbrole = rr.findByRoleName(roleName);
		
		if(roles!=null)
		{
			//if(dbrole == null)
			{
				rr.save(roles);
				logger.info("Role Added");	
			}
			//else
			//{
			//	System.out.println("Role Already Exist");
			//}
		}
		else
		{
			logger.info("Roles Does Not exist.");
			
		}
		
	}

}
