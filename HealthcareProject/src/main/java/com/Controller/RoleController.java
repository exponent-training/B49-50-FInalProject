package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Roles;
import com.Entity.User;
import com.Service.RoleService;
import com.Service.UserService;

@RestController
@RequestMapping("/exponent/roles")
public class RoleController {

	@Autowired
	private RoleService rs;

	@Autowired
	private UserService us;

	@PostMapping("/addRoles")
	public ResponseEntity<?> registerRoles(@RequestBody Roles roles) {

		rs.addRolesInService(roles);

		return new ResponseEntity("Role added", HttpStatus.CREATED);

	}

}
