package com.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Doctor;
import com.Entity.LoginDetails;
import com.Entity.User;
import com.Service.AdminService;
import com.Service.DoctorService;


String str = "MNAMD";
String str = "";

@RestController
public class AdminController {
Long l ;
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService as;
	
	@Autowired
	private DoctorService drService;

	@Autowired
	private DoctorService drService;

	@PostMapping("/assignRole/{email}/{roleName}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String roleName) {
		as.assignRolesInService(email, roleName);

		return new ResponseEntity("role assign", HttpStatus.OK);
	}

	@GetMapping("/getAllEmails")
	@Cacheable(cacheNames = "allEmails")
	public ResponseEntity<?> getAllEmails() {

		List<String> listofEmails = as.getAllEmailsFromService();

		return new ResponseEntity(listofEmails, HttpStatus.OK);
	}
	
	
	  @PostMapping("/registerDoctor") 
	  public ResponseEntity<?> RegisterDoctor(@RequestBody Doctor doctor){
	  
	  return drService.RegisterDoctorInService(doctor); 
	  
	  }
	  
		/*
		 * @DeleteMapping("/deleteUser/{did}") public ResponseEntity<?>
		 * deleteDoctor( @PathVariable int id){ return drService.deleteUserById(id); }
		 */	
	  @DeleteMapping("/deleteDoctor")
	  public ResponseEntity<?> deleteDoctor(@RequestParam int id) {
	  
		  return drService.deleteDoctorById(id);
		}


	@GetMapping("/getAllRoles")
	@Cacheable(cacheNames = "allRoles")
	public ResponseEntity<?> getAllRoles() {

		List<String> listofRoles = as.getAllRolesFromService();

		return new ResponseEntity(listofRoles, HttpStatus.OK);
	}


	public ResponseEntity<?> taskAPI() {
		return new ResponseEntity("working fine", HttpStatus.OK);
	}

	public ResponseEntity<?> taskAPIChecking() {
		return new ResponseEntity("working fine", HttpStatus.OK);
	}

	@PostMapping("/registerDoctor")
	public ResponseEntity<?> RegisterDoctor(@RequestBody Doctor doctor) {

		return drService.RegisterDoctorInService(doctor);

	}

	/*
	 * @DeleteMapping("/deleteUser/{did}") public ResponseEntity<?>
	 * deleteDoctor( @PathVariable int id){ return drService.deleteUserById(id); }
	 */
	@DeleteMapping("/deleteDoctor")
	public ResponseEntity<?> deleteDoctor(@RequestParam int id) {

		return drService.deleteDoctorById(id);
	}


}
