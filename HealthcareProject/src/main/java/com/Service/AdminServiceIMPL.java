package com.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Login;
import com.Entity.Roles;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Repository.RoleRepository;
import com.Repository.UserRepository;

@Service
public class AdminServiceIMPL implements AdminService {

	Logger logger = LoggerFactory.getLogger(AdminServiceIMPL.class);

	@Autowired
	private LoginRepository lr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private UserRepository ur;

	@Override
	public void assignRoleInService(String email, String roleName) {

		Login login = lr.findByEmail(email);

		if (login != null && login.getUser() != null) {
			Roles role = rr.findByRoleName(roleName);

			if (role != null) {

				if (!(login.getUser().getRoles().getRoleName().equalsIgnoreCase(roleName))) {

					User user = login.getUser();
					user.setRoles(role);
					ur.save(user);

					System.out.println("Role Assigned");

				} else {
					System.out.println("Role Already assigned");
				}

			} else {
				System.out.println("Role Doesnot Exist");
			}

		} else {
			System.out.println("Wrong Email");
		}

	}

	@Override
	public List<String> getAllEmailsInService() {

		List<String> listEmails = lr.getAllEmailOnly();

		System.out.println(listEmails);

		return listEmails;
	}

	@Override
	public List<String> getAllRolesInService() {

		List<String> listRoles = rr.getAllRoles();

		System.out.println(listRoles);

		return listRoles;
	}

	@Override
	public int getAdminInService(String email, String password) {

		Login admin = lr.findByEmailAndPassword(email, password);

		if (admin != null) {
			logger.info("You can update role");
			return 1;
		} else {
			logger.warn("You are not admin");
			return 0;
		}

	}

	@Override
	public int updateRoleInService(String email, String roleName) {

		Login login = lr.findByEmail(email);

		if (login != null && login.getUser() != null) {

			Roles roles = rr.findByRoleName(roleName);

			if (!(login.getUser().getRoles().getRoleName().equalsIgnoreCase(roleName))) {

				login.getUser().setRoles(roles);

				lr.save(login);

				logger.info("Role updated");
				return 1;

			} else {
				logger.warn("Role already exist");
				return 2;

			}
		} else {
			logger.warn("Role does not exist");
		}

		return 0;
	}

	@Transactional
	public int deleteRoleInService(String email, String roleName) {

		User user = ur.findByEmail(email);

		if (user != null && user.getRoles() != null) {

			Roles roles = rr.findByRoleName(roleName);

			user.getRoles().setRoleName(null);
			user.setRoles(null);

			ur.save(user);

			logger.info("Role deleted");
			return 1;

		} else {

			logger.warn("Role is null ");
			return 2;

		}

	}

}
