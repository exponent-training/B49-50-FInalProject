package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.AdminService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class AdminController {

	@Autowired
	private AdminService as;

	@PostMapping("/assignRole/{email}/{rolename}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String rolename) {

		int result = as.assignRoleInService(email, rolename);

		if (result == 1) {
			return new ResponseEntity("Role Assigned!!", HttpStatus.OK);
		} else if (result == 2) {
			return new ResponseEntity("Role already assigned!!", HttpStatus.BAD_REQUEST);
		} else if (result == 3) {
			return new ResponseEntity("Role Doesn't Exsit!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("Wrong Email!!!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAdmin/{email}/{password}")
	public ResponseEntity<?>getAdmin(@PathVariable String email,@PathVariable String password){
		
		int n=as.getAdminInService(email,password);
		if(n==1) {
			return new ResponseEntity("You can Update Users Role!!",HttpStatus.OK);
		}else {
			return new ResponseEntity("Enter Correct Email Or Password!!",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PutMapping("/updateRole/{email}/{rolename}")
	public ResponseEntity<?>updateRole(@PathVariable String email, @PathVariable String rolename) {

		int result = as.updateRoleInService(email, rolename);

		if (result == 1) {
			return new ResponseEntity("Role Updated!!", HttpStatus.OK);
		} else if (result == 2) {
			return new ResponseEntity("Role already Same!!", HttpStatus.BAD_REQUEST);
		} else if (result == 3) {
			return new ResponseEntity("Role Doesn't Exsit!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("Wrong Email!!!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllEmail")
	@Cacheable(cacheNames = "AllEmails")
	public ResponseEntity<?> getAllEmails() {
		
		List<String>Elist=as.getAllEmailInService();
		
		return new ResponseEntity(Elist,HttpStatus.OK);

	}
	
	@GetMapping("/getAllRoles")
	@Cacheable(cacheNames = "AllRoles")
	public ResponseEntity<?> getAllRoles() {
		
		List<String>Elist=as.getAllRolesInService();
		
		return new ResponseEntity(Elist,HttpStatus.OK);

	}
	
	@GetMapping("/getReport/{format}")
	public ResponseEntity<?>getReport(@PathVariable String format) throws JRException{
		
		as.getReportInService(format);
		return new ResponseEntity("report created", HttpStatus.CREATED);
	}
	
	@GetMapping("/getPatientReport/{format}")
	public ResponseEntity<?>getPatientReport(@PathVariable String format) throws JRException{
		
		as.getPatientReportInService(format);
		return new ResponseEntity("report created", HttpStatus.CREATED);
	}
}
