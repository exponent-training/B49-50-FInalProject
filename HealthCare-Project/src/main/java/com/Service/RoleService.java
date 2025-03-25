package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Roles;
import com.Repository.IRoleRepository;

@Service
public class RoleService implements IRoleService {
	@Autowired
	public IRoleRepository rolerepository;

	@Override
	public void addRolesInservice(Roles roles) {
	    if (roles == null) {
	        System.out.println("Role does not exist ...!!!");
	        return;
	    }

	    Roles DBRole = rolerepository.findByRoleName(roles.getRoleName());
	    
	    if (DBRole == null) {
	        rolerepository.save(roles);
	        System.out.println("Role Added");
	    } else {
	        System.out.println("Role already exists ...!!!");
	    }
	}


}
