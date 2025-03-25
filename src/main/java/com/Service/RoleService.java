package com.Service;

import org.springframework.http.ResponseEntity;

import com.Entity.Roles;

public interface RoleService {

	

	ResponseEntity<?> addRoleInService(Roles roles);

	ResponseEntity<?> getRoleInService();

	ResponseEntity<?> updateRoleInService(Roles role);

	ResponseEntity<?> deleteRolesInService(int id);

	ResponseEntity<?> assignRolesToUserInService(String adminEmail, String adminPassword, String userEmail,
			String roleName);

}

