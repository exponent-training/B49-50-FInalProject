package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminService.AdminserviceInterface;
import com.example.Entity.Login;
import com.example.Entity.ResponseDTO;
import com.example.Entity.Roles;
import com.example.Entity.User;
import com.example.LoginService.LoginserviceInterface;
import com.example.Roleservice.RoleServiceInterface;
import com.example.Userservice.UserServiceInterface;



@RestController
@RequestMapping("/exponent/Admin")
public class AdminController {

	Logger logger=LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminserviceInterface asi;
	
	@Autowired
	private LoginserviceInterface lsi;
	
	@Autowired
	private RoleServiceInterface rsi;
	
	@Autowired
	private UserServiceInterface usi;
	
	@GetMapping("getroles")
	@Cacheable(cacheNames = "AllRoles")
	public ResponseEntity<?> getRoles(){
		List<String> list=asi.getroles();
		if(list!=null) {
			logger.info("AllRoles"+list);
			return new ResponseEntity<List<String>>(list,HttpStatus.OK);
		}else {
			ResponseDTO dto=new ResponseDTO();
			dto.setMsg("roles does not exist");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getallEmails")
	@Cacheable(cacheNames = "AllEmails")
	public ResponseEntity<?> GetallEmails(){
		List<String> s=asi.findallEmails();
		if(s!=null) {
			logger.info("All Emails"+s);
		return new ResponseEntity<List<String>>(s,HttpStatus.OK);
	
		}else {
			ResponseDTO dto=new ResponseDTO();
			dto.setMsg("Emails does not exist");
			
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		}
	
	
	
	@PutMapping("/asignRole/{email}/{rolename}")
	public ResponseEntity<?> assignRole(@PathVariable String email,@PathVariable String rolename)
	{
		Login l=lsi.findByEmail(email);
		
		if(l!=null) {
		Roles	role=rsi.findbyrolename(rolename);
		if(!l.getUser().getRole().getRolename().equalsIgnoreCase(rolename)) {
		     User user=l.getUser();
		     user.setRole(role);
		   User u = usi.updateRole(user);
		   logger.info("Assign Role"+u);
			 return new ResponseEntity<User>(u,HttpStatus.OK);
			
		}else {
			ResponseDTO dto=new ResponseDTO();
			dto.setMsg("Role is already exist");
			return new   ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
		
		}
		}else {
			ResponseDTO dto=new ResponseDTO();
			dto.setMsg("Email does not exist");
			return new   ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<?> GetAllusers()
	{
		List<User> alluser=asi.getallUsers();
		return new ResponseEntity<List<User>>(alluser,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
}
