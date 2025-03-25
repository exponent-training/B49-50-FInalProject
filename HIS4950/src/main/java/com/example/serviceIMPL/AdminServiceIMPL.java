package com.example.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Login;
import com.example.Entity.Roles;
import com.example.Entity.User;
import com.example.Repo.LoginRepo;
import com.example.Repo.RoleRepository;
import com.example.Repo.UserRepo;
import com.example.service.AdminService;

@Service
public class AdminServiceIMPL implements AdminService {

	/*
	 * @Autowired private AdminRepo ar;
	 */

	@Autowired
	private LoginRepo lr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private UserRepo ur;

	@Override
	public void assignRolesInService(String email, String roleName) {

		Login login = lr.findByEmail(email);

		if (login != null && login.getUser() != null) {
			Roles role = rr.findByRoleName(roleName);

			if (role != null) {

				if (!(login.getUser().getRole().getRoleName().equalsIgnoreCase(roleName))) {

					User user = login.getUser();
					user.setRole(role);
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
	public List<String> getAllEmailsFromService() {

		List<String> listEmails = lr.getallEmailsOnly();

		System.out.println(listEmails);

		return listEmails;
	}

	@Override
	public List<String> getAllRolesInService() {

		return rr.getAllRoles();
	}

}
