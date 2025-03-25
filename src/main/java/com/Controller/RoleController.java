package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Roles;
import com.Service.RoleService;

@RestController
@RequestMapping("/exponent/roles")
public class RoleController {

	@Autowired
	private RoleService rs;

	@PostMapping("/addRoles")
	public ResponseEntity<?> registerRoles(@RequestBody Roles role) {
		 ResponseEntity<?> addRole = rs.addRoleInService(role);

		return new ResponseEntity("Role added", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAllRoles")
	public ResponseEntity<?> getAllRoles() {
		return rs.getRoleInService();
	}

	@PutMapping("/updateRoles")
	public ResponseEntity<?> updateRoles(@RequestBody Roles role) {
		return rs.updateRoleInService(role);
	}

	@DeleteMapping("/deleteRoles")
	public ResponseEntity<?> deleteRoles(@RequestParam int id) {
		return rs.deleteRolesInService(id);
	}

	@PutMapping("/assignRolesToUser")
	public ResponseEntity<?> assignRolesToUser(@RequestParam String adminEmail,@RequestParam String adminPassword, @RequestParam String userEmail, @RequestParam String roleName) {
		return rs.assignRolesToUserInService(adminEmail,adminPassword,userEmail,roleName) ;
	}
}


