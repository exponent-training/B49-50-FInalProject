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
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Login;
import com.example.Entity.ResponseDTO;
import com.example.Entity.Roles;
import com.example.Roleservice.RoleServiceInterface;
import com.example.Userservice.UserServiceInterface;
import com.example.util.UserNumber;

@RestController
public class Usercontroller {
	Logger logger=LoggerFactory.getLogger(Usercontroller.class);
	@Autowired
	private UserServiceInterface si;
	@Autowired
	private RoleServiceInterface rsi;
	
	@PostMapping("/RegisterUser")
	public ResponseEntity<?> RegisterUser(@RequestBody Login login)throws NullPointerException
	{
		Login LEmail =si.findByEmail(login.getEmail());
		   if(LEmail==null) {
			   if(login.getUser().getFirstName().isEmpty()||login.getEmail().isEmpty()) {
				   ResponseDTO response=new ResponseDTO();
					 response.setMsg("Name and  Email is null ");
					 logger.info("Name and  Email is null");
					   return new ResponseEntity<ResponseDTO>(response,HttpStatus.BAD_REQUEST);
				 }else {
				Roles roles= rsi.findbyrolename("NormalUser");
					 login.getUser().setUsernumber(UserNumber.getrandomnuber());
				     login.getUser().setRole(roles);
				   si.RegisterUser(login);
					 ResponseDTO response=new ResponseDTO();
					 response.setMsg("User registred successfully");
					   return new ResponseEntity<ResponseDTO>(response,HttpStatus.CREATED);
				      }
		    }else {
			   ResponseDTO response=new ResponseDTO();
				 response.setMsg("Email is Exist");
				   return new ResponseEntity<ResponseDTO>(response,HttpStatus.BAD_REQUEST);
			   
		   }
		
		
		
		
	}
	

	
	      
	    	   
	    	   
           
	@PutMapping("/updateUser/{email}/{password}")
	public ResponseEntity<?>  UpdateUser(@PathVariable String email,@PathVariable String password, @RequestBody Login login)
	{
		 Login l=si.loginUser(email,password);
		   if(l!=null) {
			  
			    login.setLid(l.getLid());
				   login.setEmail(l.getEmail());
				   login.setPassword(l.getPassword());
				   login.getUser().setUid(l.getUser().getUid());
				   login.getUser().setUsernumber(l.getUser().getUsernumber());
		   
				  Login login2=si.updateuser(login);
				   
				  return new ResponseEntity<Login>(login2,HttpStatus.CREATED);
				   
			 }else {
		  ResponseDTO res = new ResponseDTO();
		   
			 res.setMsg(" email and password is wrong");
			   return new ResponseEntity<ResponseDTO>(res,HttpStatus.BAD_REQUEST);
			
	}
	}

	
	
	
  @DeleteMapping("/delete/{email}")
  public ResponseEntity<?> DeleteUser(@PathVariable String email){
	 Login login=si.findByEmail(email);
	if(login!=null) {
	     si.DeleteUser(login);
	   
			   logger.info("user deleted");
			   ResponseDTO res = new ResponseDTO();
			   
	           res.setMsg(" user deleted successfully");
	          return new ResponseEntity<ResponseDTO>(res,HttpStatus.BAD_REQUEST);
	        }else {
     	
          logger.info("your  email is wrong");
       
          ResponseDTO res = new ResponseDTO();
		   
          res.setMsg(" your email is wrong");
         return new ResponseEntity<ResponseDTO>(res,HttpStatus.BAD_REQUEST);
        }
  }
  	
}