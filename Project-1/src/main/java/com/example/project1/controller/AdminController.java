package com.example.project1.controller;

import com.example.project1.service.AdminService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	@Autowired
	private AdminService as;

	@GetMapping("/getAllEmails")
	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmails() {
		return as.getAllEmailsInService();
	}

	@GetMapping("/getAllRoleNames")
	@Cacheable(cacheNames = "allRoles")
	public ResponseEntity<?> getAllRoles() {
		return as.getAllRolesInService();
	}

	@GetMapping("/getReport")
	public ResponseEntity<?> getReport(@RequestParam String format) throws JRException {
		 as.getReportInService(format);
		return new ResponseEntity<>("report generated",HttpStatus.OK);
	}
}
