package com.example.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminService.AdminserviceInterface;
import com.example.Doctorservice.doctorserviceinterface;
import com.example.Entity.Doctor;
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
	
	@Autowired
	private doctorserviceinterface dsi;
	
	@PostMapping("/addDoctor")
	public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor)
	{    
		Doctor d=dsi.getdoctor(doctor.getEmail());
	      boolean flag=false;
		   if(d==null)
		   {
			 flag=true;
		   }
		
		
		  if(flag)
		      {
		        Roles roles= rsi.findbyrolename("doctor");
		        doctor.setRole(roles); 
		        
	        	Doctor dr=asi.addDoctor(doctor);
	       	   
		     if(dr!=null) 
		       {
		    	  logger.info("register dr suuccessfully"+dr);
			      return new ResponseEntity<Doctor>(dr,HttpStatus.CREATED);
		       }else {
		    	      logger.info("Dr not register");
			      
		            	ResponseDTO dto=new ResponseDTO();
			            dto.setMsg("dr  can not created");
			            return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
		
		              }
	          }else {
		              ResponseDTO dto=new ResponseDTO();
		              dto.setMsg("doctor email already exist");
		              return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
	                 }
		
	}
	

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
	public ResponseEntity<?> GetallEmails()
	{
		List<String> s=asi.findallEmails();
		if(s!=null) 
		{
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
		if(alluser!=null) 
		{
		return new ResponseEntity<List<User>>(alluser,HttpStatus.OK);
		}else {
			   ResponseDTO dto=new ResponseDTO();
			   dto.setMsg("users not available");
			   return new   ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
	           }
			
  	  }
	
	
	@GetMapping("/getallDoctor")
	public ResponseEntity<?> GetAllDoctor()
	{
		List<Doctor> alldoctor=dsi.getAlldoctor();
		if(alldoctor!=null) 
		{
		return new ResponseEntity<List<Doctor>>(alldoctor,HttpStatus.OK);
		}else {
			   ResponseDTO dto=new ResponseDTO();
			   dto.setMsg("users not available");
			   return new   ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
	           }
			
  	  }
	

	@DeleteMapping("/deletedoctor/{did}")
	public ResponseEntity<?> getDoctor(@PathVariable int did)
	{
		Doctor  doctor=asi.getdoctor(did);
		if(doctor!=null)
		{          
			doctor.setRole(null);
			    int i=asi.deletedoctorbyid(doctor);
			    if(i==1)
			    {
			logger.info("Doctors successfully deleted");
			ResponseDTO dto =new ResponseDTO();
			dto.setMsg("Doctors successfully deleted");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
			    }else {
			    	logger.info("Doctors can not delete");
					ResponseDTO dto =new ResponseDTO();
					dto.setMsg("Doctors can not delete");
					return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
					  
			    }
			    	
		}else {
			ResponseDTO dto =new ResponseDTO();
			dto.setMsg("doctor email is not exist");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
		}
		}		    
			    
	
	}

	
	
	
