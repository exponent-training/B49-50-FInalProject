package com.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.config.CacheNamespaceHandler;
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

import com.Entity.Roles;
import com.Entity.User;
import com.Service.AdminService;
import com.Service.RoleService;

import lombok.Getter;

@RestController
public class AdminController {

	@Autowired
	private AdminService as;

	@Autowired
	private RoleService rs;

	@PostMapping("/assignRole/{email}/{roleName}")
	public ResponseEntity<?> assignRoles(@PathVariable String email, @PathVariable String roleName) {

		as.assignRoleInService(email, roleName);

		return new ResponseEntity("Role assign", HttpStatus.OK);

	}

	@GetMapping("/getAllEmails")
	@Cacheable(cacheNames = "AllEmails")
	public ResponseEntity<?> getAllEmails() {

		List<String> liste = as.getAllEmailsInService();

		return new ResponseEntity(liste, HttpStatus.OK);
	}

	@GetMapping("/getAllRoles")
	@Cacheable(cacheNames = "AllRoles")
	public ResponseEntity<?> getAllRoles() {

		List<String> listr = as.getAllRolesInService();

		return new ResponseEntity(listr, HttpStatus.OK);
	}

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

	public String newApi() {

		return "task api";

	}

}
