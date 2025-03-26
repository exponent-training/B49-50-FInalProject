package com.example.project1.controller;

import com.example.project1.entity.Roles;
import com.example.project1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
	@Autowired
	private RoleService as;
	@PostMapping("/addRoles")
	public ResponseEntity<?> addRoles(@RequestBody Roles role) {
		return as.addRoleInService(role);
	}

	@GetMapping("/getAllRoles")
	public ResponseEntity<?> getAllRoles() {
		return as.getRoleInService();
	}

	@PutMapping("/updateRoles")
	public ResponseEntity<?> updateRoles(@RequestBody Roles role) {
		return as.updateRoleInService(role);
	}

	@DeleteMapping("/deleteRoles")
	public ResponseEntity<?> deleteRoles(@RequestParam int id) {
		return as.deleteRolesInService(id);
	}

	@PutMapping("/assignRolesToUser")
	public ResponseEntity<?> assignRolesToUser(@RequestParam String adminEmail,@RequestParam String adminPassword, @RequestParam String userEmail, @RequestParam String roleName) {
		return as.assignRolesToUserInService(adminEmail,adminPassword,userEmail,roleName) ;
	}
}
