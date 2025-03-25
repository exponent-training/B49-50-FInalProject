package com.example.Controller;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.AdminService;

@RestController
public class AdminController
{
	@Autowired
	private AdminService as;
	
	@PostMapping("/assignRole/{email}/{roleName}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String roleName )
	{
		as.assignRolesInService(email,roleName);
		
		return new ResponseEntity("Role Assign", HttpStatus.OK);
		
	}
	
	@GetMapping("/getallEmails")
	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmails()
	{
		List<String> liste=as.getAllEmailsFromService();
		
		return new ResponseEntity(liste , HttpStatus.OK);
	}
	
	
	@GetMapping("/getallRoles")
	@Cacheable(cacheNames = "allroles" )
	public ResponseEntity<?> getAllRoles()
	{
		List<String> listr = as.getAllRolesInService();
		
		return new ResponseEntity(listr , HttpStatus.OK);
	}
	
	// Tasks
	
	/*
	@GetMapping("/getAdmin/{email}/{password}")
	public ResponseEntity<?> getAdmin(@PathVariable String email, @PathVariable String password) {
		int result = as.getAdminInService(email, password);

		if (result == 1) {
			return new ResponseEntity("You can update users role", HttpStatus.OK);
		} else {
			return new ResponseEntity("Enter correct email or passeord", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateRole/{email}/{roleName}")
	public ResponseEntity<?> updateRole(@PathVariable String email, @PathVariable String roleName) {

		int result = as.updateRoleInService(email, roleName);

		if (result == 1) {
			return new ResponseEntity("Role updated", HttpStatus.OK);
		} else if (result == 2) {
			return new ResponseEntity("Role already same", HttpStatus.BAD_REQUEST);
		} else if (result == 3) {
			return new ResponseEntity("Role does not exist", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("Wrong email", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteRole/{email}/{roleName}")
	public ResponseEntity<String> deleteUserRole(@PathVariable String email, @PathVariable String roleName) {

		int result = as.deleteRoleInService(email, roleName);

		if (result == 1) {
			return new ResponseEntity("Role deleted", HttpStatus.OK);
		} else if (result == 2) {
			return new ResponseEntity("Role is  null", HttpStatus.BAD_REQUEST);
		} else if (result == 3) {
			return new ResponseEntity("Role does not exist", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("Wrong email", HttpStatus.BAD_REQUEST);
		}
	}
}*/
}
