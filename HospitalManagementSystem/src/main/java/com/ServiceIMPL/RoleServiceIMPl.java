package com.ServiceIMPL;

import org.apache.catalina.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Controller.RoleController;
import com.Entity.Roles;
import com.Repository.RoleRepository;
import com.Service.RolesService;

@Service
public class RoleServiceIMPl implements RolesService {

	Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleRepository rr;

	@Override
	public int addRoleInService(Roles role) {
		if (role == null || role.getRoleName() == null || role.getRoleName().isEmpty()) {
			logger.warn("Role should not be null or empty");
			return 2; // Invalid role input
		}

		Roles dbrole = rr.findByRoleName(role.getRoleName());

		if (dbrole == null) {
			rr.save(role);
			logger.info("Role added successfully");
			return 0; // Success
		} else {
			logger.warn("Role already exists");
			return 1; // Role already exists
		}
	}

}
