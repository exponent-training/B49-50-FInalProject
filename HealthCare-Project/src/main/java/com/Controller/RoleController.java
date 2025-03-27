package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Roles;
import com.Service.IRoleService;

@RestController
@RequestMapping("/exponent/roles")
public class RoleController {
	
	@Autowired
	public IRoleService roleservice;
	
	@PostMapping("/addRoles")
	public ResponseEntity<?> registerRoles(@RequestBody Roles roles){
		roleservice.addRolesInservice(roles);
		System.out.println(roles);
		return new ResponseEntity("Role Added", HttpStatus.CREATED );
		
	}
// 6060/exponent/roles/addRoles
}
