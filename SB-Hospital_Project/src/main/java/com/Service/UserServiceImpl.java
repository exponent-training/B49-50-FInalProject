package com.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.Controller.UserController;
import com.Entity.LoginDetails;
import com.Entity.User;
import com.Repository.LoginDetailsRepository;
import com.Repository.UserRepository;
import com.Utility.*;

@Service
public class UserServiceImpl implements UserService{

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;


	
	@Override
	public ResponseEntity RegisterUserInService(LoginDetails loginDetails) {
	
		if(loginDetails == null || loginDetails.getUser() == null) {	
			
			logger.error("object of Login or user is null");

	        return new ResponseEntity("User Can't (is not empty) Be Null" , HttpStatus.BAD_REQUEST);
		}
	     if (loginDetails.getEmail() == null) {
	    	 
	    	 logger.error("User email is null");

	    	 return new ResponseEntity(" User Eamil Can't be Null" , HttpStatus.BAD_REQUEST);
	     }
	     if(!(loginDetailsRepository.findByEmail(loginDetails.getEmail()).isEmpty())) {
			 logger.error("User email already exists");

	    	 return new ResponseEntity("Email Already In Used" , HttpStatus.NOT_ACCEPTABLE);
	     }
	     
	     loginDetails.getUser().setUserNumber(RandomNumberGenerator.generateNumber());
	     System.out.println(loginDetails);
		 loginDetailsRepository.save(loginDetails);
		 logger.info("User saved");
		 return new ResponseEntity("User successfully added",HttpStatus.OK);

}



	@Override
	public int UpdateUserByEmail(User  user) {
		
		//User user1 = ur.findByEmail(user).get();
		
		  if(user != null) {
			  ur.save(user);
		  logger.info("User Updated ");
		  }else {
		    logger.info("user should not be null");
		    return 0;
		    } 
		  return 1;
			 /* List<LoginDetails> loginDetails = loginDetailsRepository.findByEmail(user);
		        if (loginDetails == null) {
		            return "User with email " + user + " not found.";
		        }

		        // Update the associated User
		        User user1 = ((LoginDetails) loginDetails).getUser();
		        if (user != null) {
		          
		        }

		       
		        // Save the updated LoginDetails (which will also update the User due to cascade)
		        loginDetailsRepository.save(loginDetails);

		        return "User with email " + user + " updated successfully.";
		    }
		return "enter valid user enail";*/
	      
	    }
	
	@Override
	public ResponseEntity<?> deleteUserByEmail(String email ) {
		
		//User result = ur.findByEmail(email).getEmail();
		
		 if(email != null) {
			LoginDetails login= loginDetailsRepository.getByEmail(email);
			 loginDetailsRepository.delete(login);
				
				logger.info("User Deleted ");
				return new ResponseEntity("User Deleted Successfully", HttpStatus.OK);
				}else {
					logger.info("user should not be null");
					return new ResponseEntity("enter wrong details", HttpStatus.BAD_REQUEST);
				}
		
	}
	}



	







