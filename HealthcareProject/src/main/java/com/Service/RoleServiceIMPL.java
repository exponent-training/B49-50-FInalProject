
package com.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Roles;
import com.Entity.User;
import com.Repository.RoleRepository;

@Service
public class RoleServiceIMPL implements RoleService {

	@Autowired
	private RoleRepository rr;

	Logger logger = LoggerFactory.getLogger(RoleServiceIMPL.class);

	@Override
	public void addRolesInService(Roles roles) {

		Roles dbrole = rr.findByRoleName(roles.getRoleName());

		if (roles != null) {

			if (dbrole == null) {
				rr.save(roles);
				logger.info("Roles added");

			} else {
				logger.info("Role already exist");
			}

		} else {
			logger.info("Roles does not exist");
		}

	}

}
