package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Roles;
import com.example.service.RoleService;

@RestController
@RequestMapping("/exponent/roles")
public class RoleController {

	@Autowired
	private RoleService rs;

	@PostMapping("/addRoles")
	public ResponseEntity<?> registerRoles(@RequestBody Roles role) {
		rs.addRolesInService(role);

		return new ResponseEntity("Role added", HttpStatus.CREATED);
		
		

	}
}
