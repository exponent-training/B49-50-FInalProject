package com.Service;

import java.util.List;

import com.Entity.Doctor;
import com.Entity.Roles;
import com.Entity.User;

public interface AdminService {

	public void assignRoleInService(String email, String roleName);

	List<String> getAllEmailsInService();

	List<String> getAllRolesInService();

	public int getAdminInService(String email, String password);

	public int updateRoleInService(String email, String roleName);

	public int deleteRoleInService(String email, String roleName);

}
