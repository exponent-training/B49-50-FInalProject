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
@RequestMapping("/exponent")
public class RolesController {

	@Autowired
	private RolesService rs;
	
	@PostMapping("/addRoles")
	public ResponseEntity<?>addRoles(@RequestBody Roles roles){
		
		int i=rs.addRolesInSerivce(roles);
		
		if(i==1) {
			return new ResponseEntity("Role added!!",HttpStatus.OK);
		}else {
			return new ResponseEntity("This role is already exist",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
