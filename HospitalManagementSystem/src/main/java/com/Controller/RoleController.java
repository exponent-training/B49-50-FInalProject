package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Roles;
import com.Service.RolesService;

@RestController
@RequestMapping("/Exponent")
public class RoleController {

	@Autowired
	private RolesService rs;

	@PostMapping("/addRoles")
	public ResponseEntity<?> registerRole(@RequestBody Roles role) {
		int result = rs.addRoleInService(role);

		if (result == 0) {
			return new ResponseEntity("role added", HttpStatus.CREATED);
		} else if (result == 1) {
			return new ResponseEntity("role already exist", HttpStatus.CONFLICT);
		} else if (result == 2) {
			return new ResponseEntity("role does not exist", HttpStatus.BAD_REQUEST);

		} else {

			return new ResponseEntity("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
