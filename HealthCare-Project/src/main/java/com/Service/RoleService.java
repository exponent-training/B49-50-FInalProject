package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Roles;
import com.Repository.IRoleRepository;

@Service
public class RoleService implements IRoleService {
	@Autowired
	public IRoleRepository rolerepository;

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
	public void addRolesInService(Roles roles) {

		String roleName = roles.getRoleName();

//			Role dbrole = rr.findByRoleName(roleName);

		if (roles != null) {

//				if (dbrole == null) {

			rolerepository.save(roles);
			System.out.println("Role Added");

//				} else {
//					System.out.println("Role Already Exist");
//				}

		} else {
			System.out.println("roles Does not exist.");
		}
	}

}



}
