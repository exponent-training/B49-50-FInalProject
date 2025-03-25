package com.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.LoginDetails;
import com.Entity.Roles;
import com.Entity.User;
import com.Repository.LoginDetailsRepository;
import com.Repository.RoleRepository;
import com.Repository.UserRepository;
import com.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/*
	 * @Autowired private AdminRepo ar;
	 */

	@Autowired
	private LoginDetailsRepository lr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private UserRepository ur;

	@Override
	public void assignRolesInService(String email, String roleName) {

		 LoginDetails login= lr.getLoginDetailsByEmail(email);

		if (login != null && login.getUser() != null) {
			Roles role = rr.findByRoleName(roleName);

			if (role != null) {

				if (!(login.getUser().getRole().getRoleName().equalsIgnoreCase(roleName))) {

					User user = login.getUser();
					user.setRole(role);
					ur.save(user);

					logger.info("Role Assigned");

				} else {
					logger.info("Role Already assigned");
				}

			} else {
				logger.info("Role Doesnot Exist");
			}

		} else {
			logger.info("Wrong Email");
		}

	}

	@Override
	public List<String> getAllEmailsFromService() {
		
		List<String> listEmails  =  lr.getallEmailsOnly();
		
		logger.info("All Emails");
		return listEmails;
	}

	@Override
	public List<String> getAllRolesFromService() {
		
		logger.info("All Roles");
		return rr.getAllRoles();
	}

}
