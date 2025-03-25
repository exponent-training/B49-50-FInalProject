package com.Service;

import java.util.List;

import org.apache.catalina.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Entity.LoginDetails;
import com.Entity.Roles;
import com.Repository.LoginDetailsRepository;
import com.Repository.RoleRepository;
import com.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);



	@Override
	public ResponseEntity<?> addRoleInService(Roles roles) {
		if (roles == null) {
			return new ResponseEntity<>("Role is null", HttpStatus.BAD_REQUEST);
		}
		if (roleRepository.findByRoleName(roles.getRoleName()) != null) {
			return new ResponseEntity<>("Role already exists", HttpStatus.CONFLICT);
		}
		roleRepository.save(roles);
		logger.info("{} role added", roles.getRoleName());
		return new ResponseEntity<>("Role added", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getRoleInService() {
		List<Roles> all = roleRepository.findAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateRoleInService(Roles role) {
		if ((role == null) || (role.getRoleName() == null)) {
			logger.error("Update role can't be  null");
			return new ResponseEntity<>("Update role can't be  null", HttpStatus.BAD_REQUEST);
		}
		if (roleRepository.existsById(role.getRid()) && roleRepository.findByRoleName(role.getRoleName())==null){
			logger.info("Updated role with id {} to {}", role.getRid(), role.getRoleName());
			roleRepository.save(role);
			return new ResponseEntity<>("Updated role with id " + role.getRid(), HttpStatus.OK);
		}else {
			logger.error("Updating role is present or id does not exist");
			return new ResponseEntity<>("Updating role is present or id does not exist", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteRolesInService(int id) {
		if(roleRepository.existsById(id)){
			logger.info("Deleted role with id {} from repository", id);
			roleRepository.deleteById(id);
			return new ResponseEntity<>("Role with id " + id + " deleted", HttpStatus.OK);
		}else {
			logger.error("Deleted role or id does not exist");
			return new ResponseEntity<>("Role with id " + id + " does not exist", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> assignRolesToUserInService(String adminEmail, String adminPassword, String userEmail, String roleName) {
		if (adminEmail == null || adminPassword == null || userEmail == null || roleName == null) {
			return new ResponseEntity<>("Fields can't be null", HttpStatus.BAD_REQUEST);
		}
		LoginDetails loginDetailsByEmail = loginDetailsRepository.getLoginDetailsByEmail(adminEmail);
			if (loginDetailsByEmail.getUser().getRole().getRoleName().equalsIgnoreCase("admin")) {
				if (loginDetailsByEmail.getPassword().equals(adminPassword)) {
					LoginDetails dbUser = loginDetailsRepository.getLoginDetailsByEmail(userEmail);
					Roles dbRole = roleRepository.getRolesByRoleName(roleName);
					if (dbUser == null) {
						return new ResponseEntity<>("User doesn't exist", HttpStatus.CONFLICT);
					}
					if (dbRole==null) {
						return new ResponseEntity<>("Role doesn't exist", HttpStatus.CONFLICT);
					}
					dbUser.getUser().setRole(dbRole);
					loginDetailsRepository.save(dbUser);
					return new ResponseEntity<>("User assigned to role " + dbRole.getRoleName(), HttpStatus.OK);
				}
				return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Use Admin email", HttpStatus.BAD_REQUEST);


	}



}
