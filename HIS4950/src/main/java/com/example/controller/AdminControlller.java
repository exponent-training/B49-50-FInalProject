package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AdminService;

@RestController
public class AdminControlller {

	@Autowired
	private AdminService as;

	@PostMapping("/assignRole/{email}/{roleName}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String roleName) {
		as.assignRolesInService(email, roleName);

		return new ResponseEntity("role assign", HttpStatus.OK);
	}

	@GetMapping("/getallEmails")
	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmails() {
		List<String> liste = as.getAllEmailsFromService();

		return new ResponseEntity(liste, HttpStatus.OK);

	}

	@GetMapping("/getallRoles")
	@Cacheable(cacheNames = "allroles")
	public ResponseEntity<?> getAlRoles() {
		List<String> listr = as.getAllRolesInService();

		return new ResponseEntity(listr, HttpStatus.OK);

	}
	
	//admin. -> all patient details (report) , all User details (report). => AWS S3.
  public void CheckFeature()
  {
	  
  }
	
}
