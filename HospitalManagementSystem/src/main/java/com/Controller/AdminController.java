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

import net.sf.jasperreports.engine.JRException;

@RestController
public class AdminController {
	@Autowired
	private AdminService as;

	@PostMapping("/assignedRole/{email}/{roleName}")
	public ResponseEntity<?> assignedRole(@PathVariable String email, @PathVariable String roleName) {
		as.assignedRoleInService(email, roleName);
		return new ResponseEntity("role assigned", HttpStatus.OK);

	}

	@GetMapping("/getAllEmail")
//	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmail() {
		List<String> ListOfEmail = as.getallEmailInService();
		return new ResponseEntity(ListOfEmail, HttpStatus.OK);

	}

	@GetMapping("/getAllRoles")
	@Cacheable(cacheNames = "allRoles")
	public ResponseEntity<?> getAllRoles() {
		List<String> ListOfRoles = as.getallRolesInService();
		return new ResponseEntity(ListOfRoles, HttpStatus.OK);

	}

	@GetMapping("/generateAllUserReport/{format}")
	public ResponseEntity<?> generateAllUserReport(@PathVariable String format) throws JRException {

		as.generateAllUserReportInservice(format);
		return new ResponseEntity("user report gernrated", HttpStatus.CREATED);

	}
	@GetMapping("/generatePatientsReport/{format}")
	public ResponseEntity<?> generatePatientsReport(@PathVariable String format) throws JRException {

		as.generatePatientsReportInservice(format);
		return new ResponseEntity("patients report gernrated", HttpStatus.CREATED);

	}
	
	public void checkingMethod() {
		System.out.println("checking method created");
	}

}
