package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.AdminService;


@RestController
public class AdminController {

	@Autowired
	private AdminService as;

	@PostMapping("/assignRole/{email}/{roleName}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String roleName) {
		as.assignRolesInService(email, roleName);

		return new ResponseEntity("role assign", HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmails")
	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmails(){
		
		List<String> listofEmails = as.getAllEmailsFromService();
		
		return new ResponseEntity(listofEmails , HttpStatus.OK);
	}
	
	@GetMapping("/getAllRoles")
	@Cacheable(cacheNames = "allRoles")
	public ResponseEntity<?> getAllRoles(){
		
		List<String> listofRoles = as.getAllRolesFromService();
		
		return new ResponseEntity(listofRoles , HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> taskAPI()
	{
	   return new ResponseEntity("working fine" , HttpStatus.OK);
	}

	public ResponseEntity<?> taskAPIChecking()
	{
	   return new ResponseEntity("working fine" , HttpStatus.OK);
	}
}
