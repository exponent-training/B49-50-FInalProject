package com.example.ServiceIMPL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Login;
import com.Entity.Roles;
import com.Entity.User;
import com.example.DTO.ResponseDTO;
import com.example.Repository.LoginRepostiory;
import com.example.Repository.RoleRepository;
import com.example.Repository.UserRepository;
import com.example.Service.UserService;
import com.example.util.GenerateUserNumber;

@Service
public class UserServiceIMPL implements UserService 
{
	Logger logger = LoggerFactory.getLogger(UserServiceIMPL.class);
	
	//@Autowired
	//private UserRepository ur;

	@Autowired
	private LoginRepostiory lr;
	
	@Autowired
	private RoleRepository rr;
	
	@Autowired
	private UserRepository ur;
	
	/*
	@Override
	public ResponseDTO registerUserinService(User user) {
		ResponseDTO response = new ResponseDTO();

		User ufromDB = ur.findByEmail(user.getEmail());

		if (user != null &&  user.getEmail() != null) {
			if (ufromDB == null) {
				ur.save(user);

				response.setMsg("User Added Sucessfully");

				return response;
			} else {
				response.setMsg("User already existed");
				return response;
			}
		} else {
			response.setMsg("User not register beacuse Email is empty");
			return response;
		}

	}
	*/
	
	@Override
	public ResponseDTO registerUserinService(Login login) {
		ResponseDTO response = new ResponseDTO();

		Login ufromDB = lr.findByEmail(login.getEmail());

		if (login.getUser() != null && login.getEmail() != null) {
			if (ufromDB == null) {
				
				login.getUser().setUserNumber(GenerateUserNumber.getrandomNumber());
			
				Roles roles = rr.findByRoleName("normalUser");
				
				User user = login.getUser();
				user.setRole(roles);
				
				//ur.save(user);

				lr.save(login);

				response.setMsg("User Added Sucessfully");

				return response;
			} else {
				response.setMsg("User already existed");
				return response;
			}
		} else {
			response.setMsg("User not register beacuse Email is empty");
			return response;
		}

	}
	
	
	/*
	  public User updateUserByEmail(String email, User updatedUser) {
	  Optional<User> optionalUser = ur.findByEmail(email);
	  
	  if (optionalUser.isPresent()) { User existingUser = optionalUser.get();
	  existingUser.setFirstName(updatedUser.getFirstName());
	  existingUser.setMobileno(updatedUser.getMobileno());
	  
	  return ur.save(existingUser); } else { throw new
	  RuntimeException("User notroles found with email: " + email); } }
	 */

	/*
	 * public void deleteUserByEmail(String email) {
	 * 
	 * Optional<User> userOptional = ur.findByEmail(email);
	 * 
	 * if (userOptional.isPresent()) { ur.delete(userOptional.get());
	 * logger.info("User deleted"); } else { throw new
	 * RuntimeException("User not found with email: " + email); } }
	 */

	
}
