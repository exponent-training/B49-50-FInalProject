package com.example.serviceIMPL;

import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Roles;
import com.example.Repo.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceIMPL implements RoleService {

	@Autowired
	private RoleRepository rr;

	@Override
	public void addRolesInService(Roles roles) {
		
		String roleName = roles.getRoleName();

//		Role dbrole = rr.findByRoleName(roleName);

		if (roles != null) {

//			if (dbrole == null) {

				rr.save(roles);
				System.out.println("Role Added");

//			} else {
//				System.out.println("Role Already Exist");
//			}

		} else {
			System.out.println("roles Does not exist.");
		}

	}

}
