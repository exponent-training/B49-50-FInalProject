package com.example.project1.service;

import com.example.project1.entity.Roles;
import org.springframework.http.ResponseEntity;

public interface RoleService {
	ResponseEntity<?> addRoleInService(Roles role);

	ResponseEntity<?> getRoleInService();

	ResponseEntity<?> updateRoleInService(Roles role);

	ResponseEntity<?> deleteRolesInService(int id);

	ResponseEntity<?> assignRolesToUserInService(String adminEmail, String adminPassword, String userEmail, String roleName);
}
