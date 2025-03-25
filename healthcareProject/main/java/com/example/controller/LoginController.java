package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Login;
import com.example.Entity.ResponseDTO;
import com.example.LoginService.LoginserviceInterface;
@RestController
public class LoginController {

	@Autowired
	private LoginserviceInterface lsi;
	
	
	   @GetMapping("/login/{email}/{password}")
       public ResponseEntity<?> loginUser(@PathVariable String email,@PathVariable String password)
       {
    	   Login login=lsi.loginUser(email,password);
    	   if(login!=null) {
    		   return new ResponseEntity<Login>(login,HttpStatus.OK);
    	          
    	   }else {
    		   System.out.println("your email and password is wrong");
    		   System.out.println("forgot your password");
    		   ResponseDTO response=new ResponseDTO();
				 response.setMsg("forgot your password");
				   return new ResponseEntity<ResponseDTO>(response,HttpStatus.BAD_REQUEST);
			    	   }
	
       }
	
    		@PostMapping("forgotpassword/{email}")
    		public ResponseEntity<?> forgotpassword(@PathVariable String email,@RequestBody Login login)
    		{
    		       Login login1=lsi.findByEmail(email);
    			   if(login1!=null) {
    				   if(login.getPassword()==null||login.getPassword().isEmpty())
    				   {
    					   ResponseDTO res = new ResponseDTO();
    				   
    					 res.setMsg("can not forgot your password Enter your password ");
    					   return new ResponseEntity<ResponseDTO>(res,HttpStatus.CREATED);
    					
    			        }else {
    				    login1.setLid(login1.getLid());
    					   login1.setEmail(login1.getEmail());
    					   login1.setPassword(login.getPassword());
    					  Login login2=lsi.forgotpassword(login1);
    					   
    					  return new ResponseEntity<Login>(login2,HttpStatus.CREATED);
    					   
    				 }
    		              }else {
    		              	  ResponseDTO res = new ResponseDTO();
    			   
    				           res.setMsg(" your email is wrong");
    				          return new ResponseEntity<ResponseDTO>(res,HttpStatus.BAD_REQUEST);
    				         }
    			   
    		    }
    		
    		
    	   
    	   
    	   
	
	
	
	
	
	
	
}
