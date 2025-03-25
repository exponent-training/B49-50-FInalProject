package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.Entity.ResponseDTO;
import com.example.Entity.Roles;
import com.example.Roleservice.RoleServiceInterface;

@RestController
@RequestMapping("/exponent/Role")
public class RoleController {

	Logger logger=LoggerFactory.getLogger(RoleController.class);
	
	
	@Autowired
	private RoleServiceInterface rsi;

@PostMapping("/Roleadd")
public ResponseEntity<?> AddRoles(@RequestBody Roles role){
	
	Roles roles=rsi.findbyrolename(role.getRolename());
	
	if(roles==null) {
		if(role.getRolename().isEmpty()) {
			 ResponseDTO dto=new ResponseDTO();
		       dto.setMsg("Roles  can not created");
	        	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
		     
		   }else {
			Roles r=rsi.AddRoles(role);
			return new ResponseEntity<Roles>(r,HttpStatus.CREATED);
			
			  }
	}else {
	      ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Roles already exist");
        	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      }
	}

     
@GetMapping("Rolesgetall")
public ResponseEntity<?> GetallRoles()
{
	List<Roles> roles=rsi.findRoles();
	if(roles!=null)
	{
		return new ResponseEntity<List<Roles>>(roles,HttpStatus.OK);
	}else {
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Not Find Roles");
       	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      
	}
	
}

@GetMapping("/GetRole/{rolename}")
public ResponseEntity<?> GetallRoles(@PathVariable String rolename)
{
	Roles roles=rsi.findbyrolename(rolename);
	if(roles!=null)
	{
		return new ResponseEntity<Roles>(roles,HttpStatus.OK);
	}else {
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Not Find Roles");
       	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      
	}
	
}


@DeleteMapping("/DeleteRole/{rolename}")
public ResponseEntity<?> DeleteRole(@PathVariable String rolename)
{
	Roles roles=rsi.findbyrolename(rolename);
	if(roles!=null)
	{
	   rsi.deletebyrolename(roles);	
	
	   ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Delete Roles successfully");
    	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
	 }else {
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Not Find Roles");
       	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      
	}


}

@DeleteMapping("/DeleteAllRole")
public ResponseEntity<?> DeleteRole()
{
	List<Roles> roles=rsi.findRoles();
	if(roles!=null)
	{
		rsi.deleteallrole(roles);
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg(" Deleted Roles successfully");
     
		return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
	}else {
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg(" Roles not available");
       	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      
	      
		}
		}


@PutMapping("/UpdateRole/{rolename}")
public ResponseEntity<?> UpdateRole(@PathVariable String rolename,@RequestBody Roles role)
{
	Roles roles=rsi.findbyrolename(rolename);
	if(roles!=null)
	{ 
		Roles r=rsi.updaterole(role);
		
		return new ResponseEntity<Roles>(r,HttpStatus.OK);
	}else {
		 ResponseDTO dto=new ResponseDTO();
	      dto.setMsg("Not Find Roles");
       	return new ResponseEntity<ResponseDTO>(dto,HttpStatus.NOT_ACCEPTABLE);
	      
	}
	
}













}
